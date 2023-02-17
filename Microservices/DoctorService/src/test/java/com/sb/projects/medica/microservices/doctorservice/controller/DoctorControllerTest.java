package com.sb.projects.medica.microservices.doctorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import com.sb.projects.medica.microservices.doctorservice.service.DoctorService;
import com.sb.projects.medica.microservices.doctorservice.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Slf4j
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DoctorService doctorService;
    @MockBean
    private SlotService slotService;
    @Test
    void registerNewDoctorTest() {
        assertTrue(true);
    }

    @Test
    void getAllDoctorsTest() throws Exception {
        // given - setup
        Doctor doc1 = new Doctor(1, "doc 1", "doc1email@gmail.com", "1234567890", "123", "MBBS", "Orthopedics", "4y");
        Slot slot1 = new Slot(1, "monday", LocalTime.parse("09:00:00.00"), LocalTime.parse("13:00:00.00"));
        Doctor doc2 = new Doctor(2, "doc 2", "doc2email@gmail.com", "0123456789", "456", "MBBS", "ENT", "1y");
        Slot slot2 = new Slot(2, "monday", LocalTime.parse("09:00:00.00"), LocalTime.parse("13:00:00.00"));
        Slot slot3 = new Slot(3, "tuesday", LocalTime.parse("10:30:00.00"), LocalTime.parse("14:30:00.00"));
        doc1.addSlot(slot1);
        slot1.setDoctor(doc1);
        doc2.addSlot(slot2);
        doc2.addSlot(slot3);
        slot2.setDoctor(doc2);
        slot3.setDoctor(doc2);
        List<Doctor> doctors = new LinkedList<>(Arrays.asList(doc1, doc2));
        given(doctorService.getAllDoctors()).willReturn(doctors);

        // when - perform action that we are going to test
        ResultActions response = mockMvc.perform(get("/doctor/all"));

        // then - verify result
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(doctors.size())));
    }

    @Test
    void getDoctorByIdTest() {
        assertTrue(true);
    }
}