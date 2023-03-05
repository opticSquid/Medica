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

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

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

    private HttpStatus callOtherService(Patient patient) throws URISyntaxException {
        URI uri = new URI("http://PATIENT/patient/new");
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> entity = new HttpEntity<>(patient, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return response.getStatusCode();
    }

    private HttpStatus callOtherService(Doctor doctor) throws URISyntaxException {
        URI uri = new URI("http://DOCTOR/doctor/new");
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Doctor> entity = new HttpEntity<>(doctor, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return response.getStatusCode();
    }
    private HttpStatus callOtherService(String url) throws URISyntaxException {
        URI uri = new URI(url);
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        return response.getStatusCode();
    }


    private void deleteBadUserFromUserDB(Integer id) {
        try {
            userRepo.deleteById(id);
        } catch (Exception ex) {
            log.info("Could not delete bad user having user id: {}", id);
        }
    }

    public String findUserRole(Integer id) {
        User user = findUserById(id);
        return user.getRole();
    }

    @Transactional
    public Integer addNewPatient(PatientDetailsPojo patientDetails) throws URISyntaxException {
        BasicDetailsPojo basicPatientDetails = new BasicDetailsPojo(patientDetails.getName(), patientDetails.getEmail(), patientDetails.getContactNo(), patientDetails.getPassword(), "PATIENT");
        Integer userId = addNewUser(basicPatientDetails);
        Patient patient = new Patient(patientDetails.getName(), patientDetails.getEmail(), patientDetails.getContactNo(), patientDetails.getAge(), patientDetails.getGender(), patientDetails.getMedicalConditions());
        patient.setPatId(userId);
        log.debug("Patient: {}", patient);
        HttpStatus isCreated = callOtherService(patient);
        if (isCreated != HttpStatus.CREATED) {
            deleteBadUserFromUserDB(userId);
            return null;
        }
        return userId;
    }

    @Transactional
    public Integer addNewDoctor(DoctorDetailsPojo doctorDetails) throws URISyntaxException {
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
        HttpStatus isCreated = callOtherService(doctor);
        if (isCreated != HttpStatus.CREATED) {
            deleteBadUserFromUserDB(userId);
            return null;
        }
        return userId;
    }

    @Transactional
    public Boolean deleteUser(Integer id) throws URISyntaxException {
        String role = findUserRole(id);
        String uri;
        if (Objects.equals(role, "PATIENT")) {
            uri = "http://PATIENT/patient/delete/" + id;
        } else if (Objects.equals(role, "DOCTOR")) {
            uri = "http://DOCTOR/doctor/delete/" + id;
        } else {
            return false;
        }
        HttpStatus isDeleted = callOtherService(uri);
        if (isDeleted == HttpStatus.OK) {
            userRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
