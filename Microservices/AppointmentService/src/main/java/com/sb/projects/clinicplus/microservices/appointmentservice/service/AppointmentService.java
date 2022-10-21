package com.sb.projects.clinicplus.microservices.appointmentservice.service;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Appointment;
import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Doctor;
import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.appointmentservice.pojo.AppointmentPojo;
import com.sb.projects.clinicplus.microservices.appointmentservice.repo.AppointmentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final RestTemplate restTemplate;

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    private Doctor findDoctor(Integer docId) {
        String doctorAPI = "http://DOCTOR/";
        return restTemplate.getForObject(doctorAPI + "id/" + docId.toString(), Doctor.class);
    }

    private Patient findPatient(Integer pId) {
        String patientAPI = "http://PATIENT/";
        return restTemplate.getForObject(patientAPI + "id/" + pId.toString(), Patient.class);
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment newAppointment(AppointmentPojo appointmentPojo) {
        log.debug("Appointment: " + appointmentPojo);
        Doctor doctor = findDoctor(appointmentPojo.getDocId());
        log.debug("Doctor found: " + doctor);
        Patient patient = findPatient(appointmentPojo.getPatId());
        log.debug("Patient found: " + patient);
        if (doctor == null || patient == null) {
            return null;
        } else {
            Appointment appointment = new Appointment(doctor, patient, appointmentPojo.getTime() == null ? null : appointmentPojo.getTime(), appointmentPojo.getAilment());
            appointmentRepo.save(appointment);
            return appointment;
        }
    }
}
