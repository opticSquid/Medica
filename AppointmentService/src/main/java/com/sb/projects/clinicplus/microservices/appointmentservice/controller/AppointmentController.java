package com.sb.projects.clinicplus.microservices.appointmentservice.controller;

import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Doctor;
import com.sb.projects.clinicplus.microservices.appointmentservice.entity.Patient;
import com.sb.projects.clinicplus.microservices.appointmentservice.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
@Slf4j
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    private String getCurrentContextURI(HttpServletRequest request) {
        return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString();
    }
    @GetMapping("/all")
    public String getAllAppointment() {
        return "Hello World";
    }

    //WARN:: For testing purpose only
    @GetMapping("/finddoc/{docid}")
    ResponseEntity<Doctor> findDoctor(HttpServletRequest request,@PathVariable("docid") Integer docid) {
        log.debug("finding doctor: "+docid);
        Doctor doctor = appointmentService.findDoctor(docid);
        if (doctor != null) {
            log.info("doctor having id = {}, is: \n {}", docid, doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //WARN:: For testing purpose only
    @GetMapping("/findpatient/{pid}")
    ResponseEntity<Patient> findPatient(HttpServletRequest request,@PathVariable("pid") Integer pid) {
        log.debug("finding patient: "+pid);
        Patient patient = appointmentService.findPatient(pid);
        if (patient != null) {
            log.info("patient having id = {}, is: \n {}", pid, patient);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
