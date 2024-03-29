package com.sb.projects.medica.microservices.doctorservice.service;

import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import com.sb.projects.medica.microservices.doctorservice.pojo.SlotPOJO;
import com.sb.projects.medica.microservices.doctorservice.repository.SlotRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SlotService {
    private final SlotRepo slotrepo;

    public SlotService(SlotRepo slotrepo) {
        this.slotrepo = slotrepo;
    }

    public void addTiming(List<SlotPOJO> slots) {
        for (SlotPOJO slot : slots) {
            Slot newSlot = new Slot(slot);
            slotrepo.save(newSlot);
        }
    }

    public void updateTiming(List<Slot> slots) {
        log.debug("Slots to be updated: {}", slots);
        for (Slot s : slots) {
            try {
                slotrepo.save(s);
            } catch (Exception e) {
                log.debug("Could not update slots for Slot: {} of Doctor: {}\n Reason: {}", s, s.getDoctor(), e);
            }
        }
    }

    public void deleteAllSlotsOfDoctor(Doctor doctor) {
        try {
            slotrepo.deleteByDoctor(doctor);
        } catch (Exception e) {
            log.debug("Could not delete slots for Doctor: {}\n Reason: {}", doctor, e);
        }
    }
}
