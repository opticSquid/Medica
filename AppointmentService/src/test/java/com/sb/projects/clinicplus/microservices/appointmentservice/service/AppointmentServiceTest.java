package com.sb.projects.clinicplus.microservices.appointmentservice.service;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;

    @Test
    void findPatient() {
        assertTrue(true);
    }
}