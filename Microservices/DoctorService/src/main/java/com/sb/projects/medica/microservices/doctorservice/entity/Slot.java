package com.sb.projects.medica.microservices.doctorservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sb.projects.medica.microservices.doctorservice.pojo.SlotPOJO;
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
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer slotId;
    @ManyToOne
    @JsonIgnore
    private Doctor doctor;
    private String weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime slotTime;

    public Slot(SlotPOJO slot)
    {
        this.doctor = slot.getDoctor();
        this.weekDay = slot.getWeekDay();
        this.startTime = slot.getStartTime();
        this.endTime = slot.getEndTime();
        if(slot.getSlotTime()==null)
        {
            this.slotTime = LocalTime.of(0,15,0);
        }
        else
        {
            this.slotTime = slot.getSlotTime();
        }
    }
    // Setting slot ti,e to a default value
    public Slot(Integer slotId, String weekDay, LocalTime startTime, LocalTime endTime)
    {
        this.slotId = slotId;
         this.weekDay = weekDay;
         this.startTime = startTime;
         this.endTime = endTime;
        this.slotTime = LocalTime.of(0,15,0);
    }
    // setting slot time to a user given value
    public Slot(Integer slotId, String weekDay, LocalTime startTime, LocalTime endTime, LocalTime slotTime)
    {
        this.slotId = slotId;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotTime = slotTime;
    }
}
