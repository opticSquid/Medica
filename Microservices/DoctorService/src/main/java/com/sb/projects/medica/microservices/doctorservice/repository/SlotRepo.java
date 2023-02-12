package com.sb.projects.medica.microservices.doctorservice.repository;

import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepo extends JpaRepository<Slot, Integer> {
}
