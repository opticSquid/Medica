package com.sb.projects.medica.microservices.doctorservice.service;

import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.repository.DoctorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor getDoctorByID(Integer id) {
        Optional<Doctor> doctorOptional = doctorRepo.findById(id);
        return doctorOptional.orElse(null);
    }

    public List<Doctor> getDoctorsByName(String name) {
        Optional<List<Doctor>> doctorOptional = doctorRepo.findByName(name);
        return doctorOptional.orElse(null);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        Optional<List<Doctor>> doctorOptional = doctorRepo.findBySpecialization(specialization);
        return doctorOptional.orElse(null);
    }

    public Doctor addNewDoctor(Doctor doctor) {
        log.info("Incoming doctor (to be added): " + doctor);
        try {
            doctorRepo.save(doctor);
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
        Doctor existingDoctor = getDoctorByID(id);
        if (existingDoctor != null) {
            return doctorRepo.save(doctor);
        } else {
            log.error("Doctor could not be updated, because requested doctor id could not be found");
            return null;
        }
    }

    public Boolean deleteDoctor(Integer id) {
        log.info("Incoming doctor id (to be deleted): " + id);
        try {
            doctorRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Doctor could not be deleted.. reason => " + e);
            return false;
        }
    }
}

