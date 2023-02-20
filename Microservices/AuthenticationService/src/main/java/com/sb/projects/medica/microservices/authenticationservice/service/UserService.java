package com.sb.projects.medica.microservices.authenticationservice.service;

import com.sb.projects.medica.microservices.authenticationservice.entity.User;
import com.sb.projects.medica.microservices.authenticationservice.pojo.BasicDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.DoctorDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.PatientDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Doctor;
import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Patient;
import com.sb.projects.medica.microservices.authenticationservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final RestTemplate restTemplate;

    public UserService(UserRepo userRepo, RestTemplate restTemplate) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
    }

    private User findUserById(Integer id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElse(null);
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
        Doctor doctor;
        if (doctorDetails.getSlots() != null) {
            // if slot details is provided
            doctor = new Doctor(doctorDetails.getName(), doctorDetails.getEmail(), doctorDetails.getContactNo(), doctorDetails.getRegNo(), doctorDetails.getDegree(), doctorDetails.getSpecialization(), doctorDetails.getExperience(), doctorDetails.getSlots());
        } else {
            // if slot details is not provided
            doctor = new Doctor(doctorDetails.getName(), doctorDetails.getEmail(), doctorDetails.getContactNo(), doctorDetails.getRegNo(), doctorDetails.getDegree(), doctorDetails.getSpecialization(), doctorDetails.getExperience());
        }
        doctor.setDocId(userId);
        log.debug("Doctor: {}", doctor);
        restTemplate.postForLocation("http://DOCTOR/doctor/new", doctor, Doctor.class);
        return userId;
    }

    public Boolean deleteUser(Integer id) {
        User user = findUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String uri;
        if (Objects.equals(user.getRole(), "PATIENT")) {
            uri = "http://PATIENT/patient/delete/" + id;
        } else if (Objects.equals(user.getRole(), "DOCTOR")) {
            uri = "http://DOCTOR/doctor/delete/" + id;
        } else {
            return false;
        }
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        return response.getStatusCode() == HttpStatus.OK;
    }
}
