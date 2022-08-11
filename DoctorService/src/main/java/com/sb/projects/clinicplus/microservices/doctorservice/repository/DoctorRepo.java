package com.sb.projects.clinicplus.microservices.doctorservice.repository;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    Optional<List<Doctor>> findByName(String name);
    Optional<List<Doctor>> findBySpecialization(String specialization);
}
