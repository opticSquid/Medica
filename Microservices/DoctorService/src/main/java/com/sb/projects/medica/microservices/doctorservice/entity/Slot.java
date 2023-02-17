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
    private String slotTime;

    public Slot(SlotPOJO slot)
    {
        this.doctor = slot.getDoctor();
        this.weekDay = slot.getWeekDay();
        this.startTime = slot.getStartTime();
        this.endTime = slot.getEndTime();
        this.slotTime = slot.getSlotTime();
    }
    public Slot(Integer slotId, String weekDay, LocalTime startTime, LocalTime endTime)
    {
        this.slotId = slotId;
         this.weekDay = weekDay;
         this.startTime = startTime;
         this.endTime = endTime;
    }
}
