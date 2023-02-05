package com.sb.projects.medica.microservices.doctorservice.controller;

import com.sb.projects.medica.microservices.doctorservice.pojo.TimingPOJO;
import com.sb.projects.medica.microservices.doctorservice.service.TimingService;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor/timing")
public class TimingController {
    private final TimingService timingService;

    public TimingController(TimingService timingService) {
        this.timingService = timingService;
    }
//    @PostMapping("/new")
//    public RequestEntity<String> addTiming(@RequestBody TimingPOJO timing){
//
//    }

}
