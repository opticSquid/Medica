package com.sb.projects.clinicplus.microservices.doctorservice.repository;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Doctor_repo extends JpaRepository<Doctors, Integer> {

    Optional<List<Doctors>> findByName(String name);
    Optional<List<Doctors>> findBySpecialization(String specialization);
}
