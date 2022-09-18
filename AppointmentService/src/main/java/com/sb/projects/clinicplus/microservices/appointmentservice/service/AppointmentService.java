package com.sb.projects.clinicplus.microservices.appointmentservice.service;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Appointment;
import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Doctor;
import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.appointmentservice.repo.AppointmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final RestTemplate restTemplate;
    private final String doctorAPI = "http://DOCTOR/";
    private final String patientAPI = "http://PATIENT/";

    public AppointmentService(AppointmentRepo appointmentRepo, RestTemplate restTemplate) {
        this.appointmentRepo = appointmentRepo;
        this.restTemplate = restTemplate;
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    public Doctor findDoctor(Integer docId) {
        return restTemplate.getForObject(doctorAPI+"id/"+docId.toString(), Doctor.class);
    }

    public Patient findPatient(Integer pId) {
        return restTemplate.getForObject(patientAPI+"id/"+pId.toString(), Patient.class);
    }

}
