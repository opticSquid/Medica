package com.sb.projects.medica.microservices.patientservice.repo;

import com.sb.projects.medica.microservices.patientservice.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Integer> {

}