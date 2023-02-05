package com.sb.projects.medica.microservices.doctorservice.service;

import com.sb.projects.medica.microservices.doctorservice.pojo.TimingPOJO;
import com.sb.projects.medica.microservices.doctorservice.repository.TimingRepo;
import org.springframework.stereotype.Service;

@Service
public class TimingService {
    private final TimingRepo timingrepo;

    public TimingService(TimingRepo timingrepo) {
        this.timingrepo = timingrepo;
    }

    public void addTiming(TimingPOJO timing){

    }
}
