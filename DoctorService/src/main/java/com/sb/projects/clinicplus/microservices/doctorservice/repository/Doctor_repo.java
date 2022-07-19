package com.sb.projects.clinicplus.microservices.doctorservice.repository;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Doctor_repo extends JpaRepository<Doctor, Integer> {

    Optional<List<Doctor>> findByName(String name);
    Optional<List<Doctor>> findBySpecialization(String specialization);
}
