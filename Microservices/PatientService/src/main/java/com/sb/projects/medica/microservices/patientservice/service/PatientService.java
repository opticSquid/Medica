package com.sb.projects.medica.microservices.patientservice.service;

import java.util.List;
import java.util.Optional;

import com.sb.projects.medica.microservices.patientservice.entity.Prescription;
import com.sb.projects.medica.microservices.patientservice.pojo.PrescriptionPOJO;
import org.springframework.stereotype.Service;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;
import com.sb.projects.medica.microservices.patientservice.pojo.PatientPojo;
import com.sb.projects.medica.microservices.patientservice.repo.PatientRepo;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@Service
public class PatientService {
    private final PatientRepo patientRepo;
    private final PrescriptionService presService;

    public PatientService(PatientRepo patientRepo, PrescriptionService presService) {
        this.patientRepo = patientRepo;
        this.presService = presService;
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

    @Transactional
    public Patient addNewPatient(PatientPojo incomingPatient) {
        log.info("Incoming patient (to be added): " + incomingPatient);
        try {
            Patient patient = new Patient(incomingPatient);
            patient = patientRepo.save(patient);
            log.debug("Primary save step completed");
            if (incomingPatient.getPrescriptions() != null) {
                for (PrescriptionPOJO p : incomingPatient.getPrescriptions()) {
                    p.setPatient(patient);
                }
                presService.addNewPrescription(incomingPatient.getPrescriptions());
            }
            return patient;
        } catch (Exception e) {
            log.error("new Patient could not be added.. reason =>\n" + e);
            return null;
        }
    }

    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    @Transactional
    public Patient updatePatient(Patient patient) {
        log.info("Incoming patient (to be updated): " + patient);
        Patient existingPatient = getPatientById(patient.getPatId());
        if (existingPatient != null) {
            existingPatient = new Patient(patient.getPatId(), patient.getName(), patient.getEmail(), patient.getContactNo(), patient.getAge(), patient.getGender(), patient.getMedicalConditions());
            patientRepo.save(existingPatient);
            if(patient.getPrescriptionList()!=null)
            {
                for (Prescription p : patient.getPrescriptionList()) {
                    p.setPatient(existingPatient);
                }
                presService.updatePrescription(patient.getPrescriptionList());
            }
            return existingPatient;
        } else {
            log.error("Patient could not be updated, because requested patient could not be found");
            return null;
        }
    }

    @Transactional
    public Boolean deletePatient(Integer id) {
        log.info("Incoming patient id (to be deleted): " + id);
        try {
            Patient existingPatient = getPatientById(id);
            if (existingPatient != null) {
                if(existingPatient.getPrescriptionList()!=null)
                {
                    presService.deleteAllByPatient(existingPatient);
                }
                patientRepo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Patient could not be deleted.. reason => " + e);
            return false;
        }
    }
}