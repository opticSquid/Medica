package com.sb.projects.medica.microservices.patientservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sb.projects.medica.microservices.patientservice.pojo.PrescriptionPOJO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer prescID;
    @ManyToOne
    @JsonIgnore
    Patient patient;
    @NotNull
    Integer docId;
    @NotNull
    LocalDateTime time;
    @ElementCollection
    @CollectionTable(name = "medicines", joinColumns = @JoinColumn(name = "prescId"))
    List<String> medicines = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "tests", joinColumns = @JoinColumn(name = "prescId"))
    List<String> tests = new ArrayList<>();
    String comments;
    LocalDate nextAppointmentDate;

    public Prescription(PrescriptionPOJO pojo) {
        this.patient = pojo.getPatient();
        this.time = pojo.getTime();
        this.medicines = pojo.getMedicines();
        this.tests = pojo.getTests();
        this.comments = pojo.getComments();
        this.nextAppointmentDate = pojo.getNextAppointmentDate();
    }

}
