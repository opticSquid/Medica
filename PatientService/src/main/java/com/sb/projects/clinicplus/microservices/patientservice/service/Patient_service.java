package com.sb.projects.clinicplus.microservices.patientservice.service;

import com.sb.projects.clinicplus.microservices.patientservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.patientservice.repo.Patient_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class Patient_service {
    private final Patient_repo patient_repo;

    public Patient_service(Patient_repo patient_repo) {
        this.patient_repo = patient_repo;
    }

    public Iterable<Patient> fetchAllPatients() {
        return patient_repo.findAll();
    }

    public Patient getPatientById(Integer id) {
        Optional<Patient> patient_optional = patient_repo.findById(id);
        return patient_optional.orElse(null);
    }

    public Iterable<Patient> getPatientsByName(String name) {
        Optional<List<Patient>> patient_optional = patient_repo.findByName(name);
        return patient_optional.orElse(null);
    }

    public Patient getPatientByEmail(String email) {
        Optional<Patient> patient_optional = patient_repo.findByEmail(email);
        return patient_optional.orElse(null);
    }

    public Patient addNewPatient(Patient patient) {
        log.info("Incoming patient (to be added): " + patient);
        try {
            patient_repo.save(patient);
            return patient;
        } catch (Exception e) {
            log.error("new Patient could not be added.. reason =>\n" + e);
            return null;
        }
    }
    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    public Patient updatePatient(Integer id,Patient patient) {
        log.info("Incoming patient (to be updated): " + patient);
        Patient existingPatient = getPatientById(id);
        if (existingPatient != null) {
            patient_repo.save(patient);
            return patient;
        } else {
            log.error("Patient could not be updated, because requested patient could not be found");
            return null;
        }
    }

    public boolean deletePatient(Integer id) {
        log.info("Incoming patient id (to be deleted): " + id);
        try {
            patient_repo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Patient could not be deleted.. reason => " + e);
            return false;
        }
    }
}
