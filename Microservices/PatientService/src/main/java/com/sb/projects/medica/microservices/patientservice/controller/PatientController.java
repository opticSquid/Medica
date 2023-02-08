package com.sb.projects.medica.microservices.patientservice.controller;


import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;
import com.sb.projects.medica.microservices.patientservice.pojo.PatientPojo;
import com.sb.projects.medica.microservices.patientservice.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Gets the list of all patients
     */
    @GetMapping("/all")
    public Iterable<Patient> getPatients() {
        return patientService.fetchAllPatients();
    }

    /**
     * This route returns the details of a patient whose id is being provided
     *
     * @param id is the patient id
     */
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
    public ResponseEntity<String> registerNewPatient(@RequestBody PatientPojo patient) {
        Patient savedPatient = patientService.addNewPatient(patient);
        if (savedPatient != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/patients/{id}").buildAndExpand(savedPatient.getPatId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Object can not be processed");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updatePatientDetails(@PathVariable("id") Integer id, @RequestBody PatientPojo patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
        if (updatedPatient != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/id/{id}").buildAndExpand(updatedPatient.getPatId()).toUri();
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
