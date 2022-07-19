package com.sb.projects.clinicplus.microservices.doctorservice.controller;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctor;
import com.sb.projects.clinicplus.microservices.doctorservice.service.Doctor_service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@Slf4j
public class Doctor_controller {
    private final Doctor_service doctor_service;

    public Doctor_controller(Doctor_service doctor_service) {
        this.doctor_service = doctor_service;
    }

    //Special utility
    private ResponseEntity<Iterable<Doctor>> getIterableResponseEntity(List<Doctor> doctor) {
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<Doctor> getDoctors() {
        return doctor_service.getAllDoctors();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Integer id) {
        Doctor doctor = doctor_service.getDoctorByID(id);
        if (doctor != null) {
            log.info("doctor having id = {}, is: \n {}", id, doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<Doctor>> getDoctorByName(@PathVariable("name") String name) {
        List<Doctor> doctor = doctor_service.getDoctorsByName(name);
        return getIterableResponseEntity(doctor);
    }


    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<Iterable<Doctor>> getDoctorBySpecialization(@PathVariable("specialization") String specialization) {
        List<Doctor> doctor = doctor_service.getDoctorsBySpecialization(specialization);
        return getIterableResponseEntity(doctor);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> registerNewDoctor(@RequestBody @Valid Doctor doctor) {
        Doctor saved_doctor = doctor_service.addNewDoctor(doctor);
        if (doctor != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/doctors/{id}").buildAndExpand(saved_doctor.getD_id()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Object can not be processed");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateDoctorDetails(@PathVariable("id") Integer id, @RequestBody Doctor doctor) {
        Doctor updated_doctor = doctor_service.updateDoctor(id, doctor);
        if (updated_doctor != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/doctors/{id}").buildAndExpand(updated_doctor.getD_id()).toUri();
            return ResponseEntity.status(204).location(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Update process on provided object can not be fulfilled");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Integer id) {
        if (doctor_service.deleteDoctor(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("object deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("object could not be deleted, check for existing associations of this object with other records in database");
        }
    }
}
