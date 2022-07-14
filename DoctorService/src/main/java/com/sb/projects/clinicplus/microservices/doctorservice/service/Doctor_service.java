package com.sb.projects.clinicplus.microservices.doctorservice.service;

import com.sb.projects.clinicplus.microservices.doctorservice.entity.Doctors;
import com.sb.projects.clinicplus.microservices.doctorservice.repository.Doctor_repo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Doctor_service {
    private final Doctor_repo doctor_repo;

    public Doctor_service(Doctor_repo doctor_repo) {
        this.doctor_repo = doctor_repo;
    }

    public Iterable<Doctors> getAllDoctors() {
        return doctor_repo.findAll();
    }

    public Doctors getDoctorByID(Integer id) {
        Optional<Doctors> doctor_optional = doctor_repo.findById(id);
        return doctor_optional.orElse(null);
    }

    public List<Doctors> getDoctorsByName(String name) {
        Optional<List<Doctors>> doctor_optional = doctor_repo.findByName(name);
        return doctor_optional.orElse(null);
    }

    public List<Doctors> getDoctorsBySpecz(String specz) {
        Optional<List<Doctors>> doctor_optional = doctor_repo.findBySpecialization(specz);
        return doctor_optional.orElse(null);
    }

    public Doctors addNew(Doctors doctor) {
        Doctors saved_doctor;
        try {
            saved_doctor = doctor_repo.save(doctor);
        } catch (Exception e) {
            saved_doctor = null;
        }
        return saved_doctor;
    }

    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    public Doctors update(Integer id, Doctors doctor) {
        Optional<Doctors> doctor_optional = Optional.ofNullable(getDoctorByID(id));
        if (doctor_optional.isPresent()) {
            return doctor_repo.save(doctor);
        } else {
            return null;
        }
    }

    public Boolean delete(Integer id) {
        try {
            doctor_repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

