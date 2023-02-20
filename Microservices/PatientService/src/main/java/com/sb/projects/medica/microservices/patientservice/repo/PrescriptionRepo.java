package com.sb.projects.medica.microservices.patientservice.repo;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;
import com.sb.projects.medica.microservices.patientservice.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Integer> {
    @Modifying
    @Query(value = "delete from medicines where presc_id=?1",nativeQuery = true)
    void deleteMedicinesByPatient(Integer presc_id);
    @Modifying
    @Query(value = "delete from tests where presc_id=?1",nativeQuery = true)
    void deleteTestsByPatient(Integer presc_id);
    @Modifying
    @Query(value = "DELETE FROM Prescription p WHERE p.patient =?1")
    void deleteByPatient(Patient patient);
}