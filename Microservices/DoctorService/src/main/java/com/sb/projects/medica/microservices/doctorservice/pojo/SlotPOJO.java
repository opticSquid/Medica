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
    //TODO:: We will be taking only docId and fetching the other doctor details internally
    // to reduce the amount of data transfer
    private Doctor doctor;
    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime slotTime;

    public SlotPOJO(String weekDay, LocalTime startTime, LocalTime endTime)
    {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotTime = null;
    }
    public SlotPOJO(String weekDay, LocalTime startTime, LocalTime endTime, LocalTime slotTime)
    {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotTime = slotTime;
    }
}
