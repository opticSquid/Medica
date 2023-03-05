package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    LocalDateTime time;
    List<String> medicines = new ArrayList<>();
    List<String> tests = new ArrayList<>();
    String comments;
    LocalDate nextAppointmentDate;
}
