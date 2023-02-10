package com.sb.projects.medica.microservices.doctorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer slotId;
    @ManyToOne
    private Doctor doctor;
    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotTime;
}
