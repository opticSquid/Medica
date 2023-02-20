package com.sb.projects.medica.microservices.patientservice.service;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;
import com.sb.projects.medica.microservices.patientservice.entity.Prescription;
import com.sb.projects.medica.microservices.patientservice.pojo.PrescriptionPOJO;
import com.sb.projects.medica.microservices.patientservice.repo.PrescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PrescriptionService {
    private final PrescriptionRepo presRepo;

    public PrescriptionService(PrescriptionRepo presRepo) {
        this.presRepo = presRepo;
    }

    public void addNewPrescription(List<PrescriptionPOJO> pojoList) {
        for (PrescriptionPOJO p : pojoList) {
            Prescription tobeSaved = new Prescription(p);
            presRepo.save(tobeSaved);
        }
    }

    public void updatePrescription(List<Prescription> prescriptions) {
        for(Prescription p:prescriptions)
        {
            try{
                presRepo.save(p);
            } catch (Exception e) {
                log.debug("Could not update prescriptions for prescription: {} of Patient: {}\n Reason: {}", p, p.getPatient(), e);
            }

        }
    }

    public void deleteAllByPatient(Patient patient) {
        try{
            presRepo.deleteMedicinesByPatient(patient.getPatId());
            presRepo.deleteTestsByPatient(patient.getPatId());
            presRepo.deleteByPatient(patient);
        } catch (Exception e) {
            log.debug("Could not delete prescriptions for Patient: {}\n Reason: {}", patient, e);
        }
    }
}
