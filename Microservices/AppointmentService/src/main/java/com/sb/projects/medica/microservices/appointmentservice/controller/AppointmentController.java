package com.sb.projects.medica.microservices.appointmentservice.controller;

import com.sb.projects.medica.microservices.appointmentservice.entity.Appointment;
import com.sb.projects.medica.microservices.appointmentservice.pojo.AppointmentPojo;
import com.sb.projects.medica.microservices.appointmentservice.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/appointment")
@Slf4j
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    private String getCurrentContextURI(HttpServletRequest request) {
        return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString();
    }

    @GetMapping("/all")
    public Iterable<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/new")
    public ResponseEntity<String> newAppointment(@RequestBody AppointmentPojo appointmentPojo) {
        log.debug("New incoming patient: " + appointmentPojo);
        Appointment savedAppointment = appointmentService.newAppointment(appointmentPojo);
        if (savedAppointment != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/id/{id}").buildAndExpand(savedAppointment.getApmntId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Object can not be processed");
        }
    }

}
