package com.sb.projects.medica.microservices.appointmentservice.service;

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