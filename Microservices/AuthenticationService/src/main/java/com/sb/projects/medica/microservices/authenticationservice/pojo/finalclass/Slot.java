package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    private Integer slotId;

    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotTime;
}
