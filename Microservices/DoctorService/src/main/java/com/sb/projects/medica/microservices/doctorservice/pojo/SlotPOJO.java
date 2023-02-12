package com.sb.projects.medica.microservices.doctorservice.pojo;

import com.sb.projects.medica.microservices.doctorservice.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotPOJO {
    // We will be taking only docId and fetching the other doctor details internally
    // to reduce the amount of data transfer
    private Doctor doctor;
    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotTime="15";
}
