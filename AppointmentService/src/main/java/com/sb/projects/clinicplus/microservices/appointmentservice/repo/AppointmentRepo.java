package com.sb.projects.clinicplus.microservices.appointmentservice.repo;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
}
