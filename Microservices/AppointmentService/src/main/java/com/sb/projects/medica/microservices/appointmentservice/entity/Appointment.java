package com.sb.projects.medica.microservices.appointmentservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer apmntId;
    @OneToOne
    private Doctor doctor;
    @OneToOne
    private Patient patient;
    @ElementCollection
    @CollectionTable(name = "prescriptions", joinColumns = @JoinColumn(name = "apmntId"))
    private List<String> prescription;
    private String ailment;
    private LocalDateTime time;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime time, String ailment) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time == null ? LocalDateTime.now() : time;
        this.ailment = ailment;
    }
}
