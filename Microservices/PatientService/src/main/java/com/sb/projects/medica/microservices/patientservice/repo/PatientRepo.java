package com.sb.projects.medica.microservices.patientservice.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Optional<List<Patient>> findByName(String name);
    //TODO: Need to find how to use this query to further optimize update operations
    @Modifying
    @Query("update Patient p set p.contactNo=?2, p.email=?3, p.name=?4 where p.patId=?1")
    void updatePatient(Integer patId, String contactNo, String email, String name);

    Optional<Patient> findByEmail(String email);
}