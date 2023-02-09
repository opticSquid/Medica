#! /bin/bash
echo "are packages up to date? (y/N)"
read up2Date
if [ $up2Date == "N" ]; then
    echo "Creating JAR of Eureka Serrver"
    cd ./EurekaServer
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Application Gateway"
    cd ./Gateway
    mvn clean package -DskipTests
    cd ../
    echo "Starting to create JAR of own services"
    cd ./Microservices
    echo "Creating JAR of Patient Service"
    cd ./PatientService
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Doctor Service"
    cd ./DoctorService
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Authentication Service"
    cd ./AuthenticationService
    mvn clean package -DskipTests
    cd ../
    # echo "Creating JAR of Appointment Service"
    # cd ./AppointmentService
    # mvn clean package -DskipTests
    # cd ../../
    cd ../
    echo "Building executables completed"
else
    echo "skipping building packages"
fi
echo "is Docker desktop already running? (y/N)"
read isDockerRunning
if [ $isDockerRunning == "N" ]; then
    echo "Now attempting to start docker desktop"
    systemctl --user start docker-desktop
    echo "waiting for docker desktop services to start properly -  waiting 5 mins"
    sleep 300
    echo "Docker Desktop Successfully started"
else
    echo "skipping starting of docker desktop"
fi
echo "Is mysqldb, service discovery, app gateway already running? (y/N)"
read isEssnRunning
if [ $isEssnRunning == "N" ]; then
    echo "Attempting to start essential container services"
    docker compose up -d mysqldb service-discovery app-gateway
    echo "waiting for all the services to come online - waiting 2 mins"
    sleep 120
    echo "Essential Services Started"
else
    echo "skipping starting of essential services"
fi
echo "deleting old containers"
docker rm patient-service doctor-service auth-service
echo "deleting old images"
docker rmi medica-patient-service medica-doctor-service medica-auth-service
echo "starting own services"
docker compose up  -d patient-service doctor-service auth-service
echo "all containers started"
echo "start up successful exiting"
