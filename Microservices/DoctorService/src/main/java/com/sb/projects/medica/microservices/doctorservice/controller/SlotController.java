package com.sb.projects.medica.microservices.doctorservice.controller;

import com.sb.projects.medica.microservices.doctorservice.service.SlotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor/timing")
public class SlotController {
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }
//    @PostMapping("/new")
//    public RequestEntity<String> addTiming(@RequestBody TimingPOJO timing){
//
//    }

}
