package com.sb.projects.medica.microservices.doctorservice.repository;

import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepo extends JpaRepository<Slot, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Slot s WHERE s.doctor=?1")
    void deleteByDoctor(Doctor doctor);
}
