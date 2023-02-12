package com.sb.projects.medica.microservices.authenticationservice.service;

import com.sb.projects.medica.microservices.authenticationservice.entity.User;
import com.sb.projects.medica.microservices.authenticationservice.pojo.BasicDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.DoctorDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.PatientDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Doctor;
import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Patient;
import com.sb.projects.medica.microservices.authenticationservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final RestTemplate restTemplate;

    public UserService(UserRepo userRepo, RestTemplate restTemplate) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
    }

    private Integer addNewUser(BasicDetailsPojo basicDetailsPojo) {
        try {
            User tobeSaved = new User(basicDetailsPojo);
            userRepo.save(tobeSaved);
            return tobeSaved.getUserID();
        } catch (IllegalStateException ex) {
            return null;
        }
    }

    public Integer addNewPatient(PatientDetailsPojo patientDetails) {
        BasicDetailsPojo basicPatientDetails = new BasicDetailsPojo(patientDetails.getName(), patientDetails.getEmail(), patientDetails.getContactNo(), patientDetails.getPassword(), "PATIENT");
        Integer userId = addNewUser(basicPatientDetails);
        //This patient should be sent to patient microservice
        Patient patient = new Patient(patientDetails.getName(), patientDetails.getEmail(), patientDetails.getContactNo(), patientDetails.getAge(), patientDetails.getGender(), patientDetails.getMedicalConditions());
        patient.setPatId(userId);
        log.debug("Patient: {}", patient);
        restTemplate.postForLocation("http://PATIENT/patient/new", patient, Patient.class);
        return userId;
    }

    public Integer addNewDoctor(DoctorDetailsPojo doctorDetails) {
        BasicDetailsPojo basicDoctorDetails = new BasicDetailsPojo(doctorDetails.getName(), doctorDetails.getEmail(), doctorDetails.getContactNo(), doctorDetails.getPassword(), "DOCTOR");
        Integer userId = addNewUser(basicDoctorDetails);
        // This doctor should be sent to doctor microservice
        Doctor doctor = new Doctor(doctorDetails.getName(), doctorDetails.getEmail(), doctorDetails.getContactNo(), doctorDetails.getRegNo(), doctorDetails.getDegree(), doctorDetails.getSpecialization(), doctorDetails.getExperience(), doctorDetails.getSlots());
        doctor.setDocId(userId);
        log.debug("Doctor: {}",doctor);
        restTemplate.postForLocation("http://DOCTOR/doctor/new",doctor,Doctor.class);
        return userId;
    }

}
