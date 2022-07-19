package com.sb.projects.clinicplus.microservices.patientservice.controller;


import com.sb.projects.clinicplus.microservices.patientservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.patientservice.service.Patient_service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/patients")
@Slf4j
public class Patient_controller {
    private final Patient_service patientService;

    public Patient_controller(Patient_service patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public Iterable<Patient> getPatients() {
        return patientService.fetchAllPatients();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            log.info("patient having id = {}, is: \n {}", id, patient);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<Patient>> getPatientsByName(@PathVariable("name") String name) {
        Iterable<Patient> patient = patientService.getPatientsByName(name);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> getPatientsByEmail(@PathVariable("email") String email) {
        Patient patient = patientService.getPatientByEmail(email);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> registerNewPatient(@RequestBody Patient patient) {
        Patient saved_patient = patientService.addNewPatient(patient);
        if (saved_patient != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patients/{id}").buildAndExpand(saved_patient.getP_id()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Object can not be processed");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updatePatientDetails(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        Patient updated_patient = patientService.updatePatient(id, patient);
        if (updated_patient != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patients/{id}").buildAndExpand(updated_patient.getP_id()).toUri();
            return ResponseEntity.status(204).location(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Update process on provided object can not be fulfilled");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
        boolean deletedPatient = patientService.deletePatient(id);
        if (deletedPatient) {
            return ResponseEntity.status(HttpStatus.OK).body("object deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("object could not be deleted, check for existing associations of this object with other records in database");
        }
    }
}
