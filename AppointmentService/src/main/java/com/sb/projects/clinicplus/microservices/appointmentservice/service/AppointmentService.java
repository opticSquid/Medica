package com.sb.projects.clinicplus.microservices.appointmentservice.service;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Appointment;
import com.sb.projects.clinicplus.microservices.appointmentservice.repo.AppointmentRepo;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    public AppointmentService(AppointmentRepo appointmentRepo){
        this.appointmentRepo = appointmentRepo;
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepo.findById(id).orElse(null);
    }


}
