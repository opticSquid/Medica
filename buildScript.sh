#! /bin/bash
echo "...Building Medica..."
echo "are packages up to date? (y/N)"
read -r up2Date
if [ "$up2Date" == "N" ]; then
    echo "Creating JAR of Eureka Server"
    cd ./EurekaServer || return
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Application Gateway"
    cd ./Gateway || return
    mvn clean package -DskipTests
    cd ../
    echo "Starting to create JAR of own services"
    cd ./Microservices || return
    echo "Creating JAR of Patient Service"
    cd ./PatientService || return
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Doctor Service"
    cd ./DoctorService || return
    mvn clean package -DskipTests
    cd ../
    echo "Creating JAR of Authentication Service"
    cd ./AuthenticationService || return
    mvn clean package -DskipTests
    cd ../
#     echo "Creating JAR of Appointment Service"
#     cd ./AppointmentService || return
#     mvn clean package -DskipTests
#     cd ../../
    cd ../
    echo "Building executables completed"
else
    echo "skipping building packages"
fi
echo "is Docker desktop already running? (y/N)"
read -r isDockerRunning
if [ "$isDockerRunning" == "N" ]; then
    echo "Now attempting to start docker desktop"
    systemctl --user start docker-desktop
    echo "waiting for docker desktop services to start properly -  waiting 2 mins"
    sleep 120
    echo "Docker Desktop Successfully started"
else
    echo "skipping starting of docker desktop"
fi
echo "do service discovery, app-gateway require removing and rebuilding? (y/N)"
read -r isRebuildNess
if [ "$isRebuildNess" == "N" ]; then
  echo "skipping removing the essential services"
else
  echo "deleting old containers of service-discovery and app-gateway"
  docker rm service-discovery app-gateway
  echo "deleting old images of service-discovery and app-gateway"
  docker rmi opticsquid/service-discovery opticsquid/app-gateway
fi
echo "Is mysqldb, service discovery, app gateway already running? (y/N)"
read -r isEssnRunning
if [ "$isEssnRunning" == "N" ]; then
    echo "Attempting to start essential container services"
    docker compose up -d mysqldb service-discovery app-gateway
    echo "waiting for all the services to come online - waiting 1 mins"
    sleep 60
    echo "Essential Services Started"
else
    echo "skipping starting of essential services"
fi
echo "deleting old containers"
docker rm patient-service doctor-service auth-service
echo "deleting old images"
docker rmi opticsquid/patient-service opticsquid/doctor-service opticsquid/auth-service
echo "starting own services"
docker compose up  -d patient-service doctor-service auth-service
echo "all containers started"
echo "start up successful exiting"