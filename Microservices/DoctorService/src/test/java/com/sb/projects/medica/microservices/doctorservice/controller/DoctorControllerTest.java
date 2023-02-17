package com.sb.projects.medica.microservices.doctorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import com.sb.projects.medica.microservices.doctorservice.pojo.DoctorPOJO;
import com.sb.projects.medica.microservices.doctorservice.pojo.SlotPOJO;
import com.sb.projects.medica.microservices.doctorservice.service.DoctorService;
import com.sb.projects.medica.microservices.doctorservice.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        ResultActions response = mockMvc.perform(get("/doctor/all").contentType(MediaType.APPLICATION_JSON));

        // then - verify result
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(doctors.size()))).andExpect(jsonPath("$[0].name", is("doc 1")));
    }

    @Test
    void registerNewDoctorTest() throws Exception {
        // given - set up
        SlotPOJO slot1 = new SlotPOJO("monday", LocalTime.parse("09:00:00.00"), LocalTime.parse("13:00:00.00"), LocalTime.parse("00:15:00.00"));
        SlotPOJO slot2 = new SlotPOJO("tuesday", LocalTime.parse("09:00:00.00"), LocalTime.parse("13:00:00.00"), LocalTime.parse("00:15:00.00"));
        List<SlotPOJO> slotPOJOList = new ArrayList<>(Arrays.asList(slot1, slot2));
        DoctorPOJO doctorPOJO = new DoctorPOJO(1, "doctor1", "doc1email@gmail.com", "1234567890", "123", "MBBS", "Orthopedics", "4y", slotPOJOList);
        Doctor doc = new Doctor(1, "doctor1", "doc1email@gmail.com", "1234567890", "123", "MBBS", "Orthopedics", "4y");
        given(doctorService.addNewDoctor(doctorPOJO)).willReturn(doc);

        // when - perform actions to be tested
        ResultActions response = mockMvc.perform(post("/doctor/new").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctorPOJO)).accept(MediaType.APPLICATION_JSON));

        // then - verify the results
        response.andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void getDoctorByIdTest() {
        assertTrue(true);
    }
}