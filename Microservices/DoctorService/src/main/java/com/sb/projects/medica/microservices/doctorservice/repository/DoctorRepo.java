package com.sb.projects.medica.microservices.doctorservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    Optional<List<Doctor>> findByName(String name);

    Optional<List<Doctor>> findBySpecialization(String specialization);
}
