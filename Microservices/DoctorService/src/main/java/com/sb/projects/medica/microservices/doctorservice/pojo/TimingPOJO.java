package com.sb.projects.medica.microservices.doctorservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimingPOJO {
    // We will be taking only docId and fetching the other doctor details internally
    // to reduce the amount of data transfer
    private Integer docId;
    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotTime="15";
}
