package com.sb.projects.clinicplus.microservices.doctorservice.controller;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctors;
import com.sb.projects.clinicplus.microservices.doctorservice.service.Doctor_service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    private ResponseEntity<Iterable<Doctors>> getIterableResponseEntity(List<Doctors> doctor) {
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<Doctors> getDoctors() {
        return doctor_service.getAllDoctors();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable("id") Integer id) {
        Doctors doctor = doctor_service.getDoctorByID(id);
        if (doctor != null) {
            log.info("doctor having id = {}, is: \n {}",id, doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<Doctors>> getDoctorByName(@PathVariable("name") String name) {
        List<Doctors> doctor = doctor_service.getDoctorsByName(name);
        return getIterableResponseEntity(doctor);
    }


    @GetMapping("/specz/{specz}")
    public ResponseEntity<Iterable<Doctors>> getDoctorBySpecialization(@PathVariable("specz") String specz) {
        List<Doctors> doctor = doctor_service.getDoctorsBySpecz(specz);
        return getIterableResponseEntity(doctor);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Doctors> registerNewDoctor(@RequestBody Doctors doctor) {
        Doctors saved_doctor = doctor_service.addNew(doctor);
        if (doctor != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/doctors/{id}").buildAndExpand(saved_doctor.getD_id()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Doctors> updateDoctorDetails(@PathVariable("id") Integer id, @RequestBody Doctors doctor) {
        Doctors ifUpdated = doctor_service.update(id, doctor);
        if (ifUpdated != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/doctors/{id}").buildAndExpand(ifUpdated.getD_id()).toUri();
            return ResponseEntity.status(204).location(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Integer id) {
        if (doctor_service.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
