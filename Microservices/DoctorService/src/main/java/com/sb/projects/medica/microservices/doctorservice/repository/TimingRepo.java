package com.sb.projects.medica.microservices.doctorservice.repository;

import com.sb.projects.medica.microservices.doctorservice.entity.Timing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimingRepo extends JpaRepository<Timing, Integer> {
}
