package com.sb.projects.clinicplus.microservices.patientservice.repo;


import com.sb.projects.clinicplus.microservices.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Optional<List<Patient>> findByName(String name);

    Optional<Patient> findByEmail(String email);
}
