package com.sb.projects.clinicplus.microservices.appointmentservice.controller;

import com.sb.projects.clinicplus.microservices.appointmentservice.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public String testAppointment() {
        return "Hello World";
    }
}
