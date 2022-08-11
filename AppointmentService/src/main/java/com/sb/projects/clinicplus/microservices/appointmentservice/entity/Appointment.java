package com.sb.projects.clinicplus.microservices.appointmentservice.entity;
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
    private LocalDateTime time;
    private String ailment;
    @ElementCollection
    @CollectionTable(name = "prescriptions", joinColumns = @JoinColumn(name = "apmntId"))
    private List<String> prescription;

    public void setTime(LocalDateTime time) {

        this.time = time == null ? LocalDateTime.now() : time;
    }
}
