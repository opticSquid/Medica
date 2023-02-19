package com.sb.projects.medica.microservices.patientservice.pojo;

import com.sb.projects.medica.microservices.patientservice.entity.Patient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionPOJO {
    Patient patient;
    LocalDateTime time;
    List<String> medicines = new ArrayList<>();
    List<String> tests = new ArrayList<>();
    String comments;
    LocalDate nextAppointmentDate;
}
