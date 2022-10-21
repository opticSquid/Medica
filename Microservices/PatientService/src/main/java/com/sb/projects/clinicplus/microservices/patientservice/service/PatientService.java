package com.sb.projects.clinicplus.microservices.patientservice.service;

import com.sb.projects.clinicplus.microservices.patientservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.patientservice.pojo.PatientPojo;
import com.sb.projects.clinicplus.microservices.patientservice.repo.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientService {
    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Iterable<Patient> fetchAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(Integer id) {
        Optional<Patient> patientOptional = patientRepo.findById(id);
        return patientOptional.orElse(null);
    }

    public Iterable<Patient> getPatientsByName(String name) {
        Optional<List<Patient>> patientOptional = patientRepo.findByName(name);
        return patientOptional.orElse(null);
    }

    public Patient getPatientByEmail(String email) {
        Optional<Patient> patientOptional = patientRepo.findByEmail(email);
        return patientOptional.orElse(null);
    }

    public Patient addNewPatient(PatientPojo patient) {
        log.info("Incoming patient (to be added): " + patient);
        try {
            Patient tobeSaved = new Patient(patient);
            patientRepo.save(tobeSaved);
            return tobeSaved;
        } catch (Exception e) {
            log.error("new Patient could not be added.. reason =>\n" + e);
            return null;
        }
    }

    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    public Patient updatePatient(Integer id, PatientPojo patient) {
        log.info("Incoming patient (to be updated): " + patient);
        Patient existingPatient = getPatientById(id);
        if (existingPatient != null) {
            Patient tobeSaved = new Patient(id, patient.getName(), patient.getEmail(), patient.getContactNo());
            patientRepo.save(tobeSaved);
            return tobeSaved;
        } else {
            log.error("Patient could not be updated, because requested patient could not be found");
            return null;
        }
    }

    public boolean deletePatient(Integer id) {
        log.info("Incoming patient id (to be deleted): " + id);
        try {
            patientRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Patient could not be deleted.. reason => " + e);
            return false;
        }
    }
}
