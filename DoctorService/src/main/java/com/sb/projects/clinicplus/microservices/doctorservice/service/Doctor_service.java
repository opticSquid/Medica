package com.sb.projects.clinicplus.microservices.doctorservice.service;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctor;
import com.sb.projects.clinicplus.microservices.doctorservice.repository.Doctor_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class Doctor_service {
    private final Doctor_repo doctor_repo;

    public Doctor_service(Doctor_repo doctor_repo) {
        this.doctor_repo = doctor_repo;
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctor_repo.findAll();
    }

    public Doctor getDoctorByID(Integer id) {
        Optional<Doctor> doctor_optional = doctor_repo.findById(id);
        return doctor_optional.orElse(null);
    }

    public List<Doctor> getDoctorsByName(String name) {
        Optional<List<Doctor>> doctor_optional = doctor_repo.findByName(name);
        return doctor_optional.orElse(null);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        Optional<List<Doctor>> doctor_optional = doctor_repo.findBySpecialization(specialization);
        return doctor_optional.orElse(null);
    }

    public Doctor addNewDoctor(Doctor doctor) {
        log.info("Incoming doctor (to be added): " + doctor);
        try {
            doctor_repo.save(doctor);
            return doctor;
        } catch (Exception e) {
            log.error("new doctor could not be added.. reason =>\n" + e);
            return null;
        }
    }

    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    public Doctor updateDoctor(Integer id, Doctor doctor) {
        log.info("Incoming doctor (to be updated): " + doctor);
        Doctor existing_doctor = getDoctorByID(id);
        if (existing_doctor != null) {
            return doctor_repo.save(doctor);
        } else {
            log.error("Patient could not be updated, because requested patient could not be found");
            return null;
        }
    }

    public Boolean deleteDoctor(Integer id) {
        log.info("Incoming doctor id (to be deleted): " + id);
        try {
            doctor_repo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Doctor could not be deleted.. reason => " + e);
            return false;
        }
    }
}

