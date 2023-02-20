package com.sb.projects.medica.microservices.authenticationservice.controller;

import com.sb.projects.medica.microservices.authenticationservice.pojo.DoctorDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.PatientDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup/patient")
    public ResponseEntity<String> addNewPatient(@RequestBody @Valid PatientDetailsPojo patientDetails) {
        Integer userId = userService.addNewPatient(patientDetails);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<String> addNewDoctor(@RequestBody @Valid DoctorDetailsPojo doctorDetails) {
        Integer userId = userService.addNewDoctor(doctorDetails);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        if (Boolean.TRUE.equals(userService.deleteUser(id))) {
            return ResponseEntity.status(HttpStatus.OK).body("user deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user could not be deleted, check for existing associations of this object with other records in database");
        }
    }
}
