package com.sb.projects.medica.microservices.doctorservice.service;

import com.sb.projects.medica.microservices.doctorservice.entity.Slot;
import com.sb.projects.medica.microservices.doctorservice.pojo.SlotPOJO;
import com.sb.projects.medica.microservices.doctorservice.repository.SlotRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {
    private final SlotRepo slotrepo;

    public SlotService(SlotRepo slotrepo) {
        this.slotrepo = slotrepo;
    }

    public void addTiming(List<SlotPOJO> slots){
        for(SlotPOJO slot: slots)
        {
            Slot newSlot = new Slot(slot);
            slotrepo.save(newSlot);
        }
    }
}
