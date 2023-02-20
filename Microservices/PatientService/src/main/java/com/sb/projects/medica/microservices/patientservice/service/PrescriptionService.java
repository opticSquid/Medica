package com.sb.projects.medica.microservices.patientservice.service;

import com.sb.projects.medica.microservices.patientservice.entity.Prescription;
import com.sb.projects.medica.microservices.patientservice.pojo.PrescriptionPOJO;
import com.sb.projects.medica.microservices.patientservice.repo.PrescriptionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepo presRepo;

    public PrescriptionService(PrescriptionRepo presRepo) {
        this.presRepo = presRepo;
    }

    public void addNewPrescription(List<PrescriptionPOJO> pojoList) {
        for(PrescriptionPOJO p: pojoList)
        {
            Prescription tobeSaved = new Prescription(p);
            presRepo.save(tobeSaved);
        }
    }

}
