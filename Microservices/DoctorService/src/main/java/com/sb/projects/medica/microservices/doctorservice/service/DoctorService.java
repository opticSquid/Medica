package com.sb.projects.medica.microservices.doctorservice.service;

import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import com.sb.projects.medica.microservices.doctorservice.pojo.DoctorPOJO;
import com.sb.projects.medica.microservices.doctorservice.pojo.SlotPOJO;
import com.sb.projects.medica.microservices.doctorservice.repository.DoctorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DoctorService {
    private final DoctorRepo doctorRepo;
    private final SlotService slotService;

    public DoctorService(DoctorRepo doctorRepo, SlotService slotService) {
        this.doctorRepo = doctorRepo;
        this.slotService = slotService;
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
    @Transactional
    public Doctor addNewDoctor(DoctorPOJO incomingDoctor) {
        log.info("Incoming doctor (to be added): " + incomingDoctor);
        try {
            Doctor doctor = new Doctor(incomingDoctor);
            doctor = doctorRepo.save(doctor);
            log.debug("primary save step succeeded");
            for (SlotPOJO s : incomingDoctor.getSlots()
            ) {
                s.setDoctor(doctor);
            }
            slotService.addTiming(incomingDoctor.getSlots());
            return doctor;
        } catch (Exception e) {
            log.error("new doctor could not be added.. reason =>\n" + e);
            return null;
        }
    }

    //TODO: Code can further be optimized by taking only id and only the field to update
    //TODO: update can be overloaded with one update just updating single field using id only
    //TODO: Another update updating the whole record
    @Transactional
    public Doctor updateDoctor(Doctor doctor) {
        log.info("Incoming doctor (to be updated): " + doctor);
        Doctor existingDoctor = getDoctorByID(doctor.getDocId());
        if (existingDoctor != null) {
            existingDoctor = new Doctor(doctor.getDocId(), doctor.getName(), doctor.getEmail(), doctor.getContactNo(), doctor.getRegNo(), doctor.getDegree(), doctor.getSpecialization(), doctor.getExperience());
            for (Slot s : doctor.getSlots()) {
                s.setDoctor(existingDoctor);
            }
            slotService.updateTiming(doctor.getSlots());
            return doctorRepo.save(existingDoctor);
        } else {
            log.error("Doctor could not be updated, because requested doctor id could not be found");
            return null;
        }
    }

    @Transactional
    public Boolean deleteDoctor(Integer id) {
        log.info("Incoming doctor id (to be deleted): " + id);
        try {
            Doctor existingDoctor = getDoctorByID(id);
            if(existingDoctor!=null)
            {
                slotService.deleteAllSlotsOfDoctor(existingDoctor);
                doctorRepo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Doctor could not be deleted.. reason => " + e);
            return false;
        }
    }
}

