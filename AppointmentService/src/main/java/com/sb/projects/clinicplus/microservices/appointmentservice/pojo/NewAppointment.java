package com.sb.projects.clinicplus.microservices.appointmentservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
public class NewAppointment {
    private Integer pId;
    private Integer docId;
    private LocalDateTime time;
    private String ailment;
}
