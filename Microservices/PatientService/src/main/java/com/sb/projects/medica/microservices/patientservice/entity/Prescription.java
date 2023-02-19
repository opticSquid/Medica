package com.sb.projects.medica.microservices.patientservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    @Id
    String prescID;
    @ManyToOne
    @JsonIgnore
    Patient patient;
    LocalDateTime time;
    List<String> medicines = new ArrayList<>();
    List<String> tests = new ArrayList<>();
    String comments;
    LocalDate nextAppointmentDate;

}
