package com.sb.projects.clinicplus.microservices.appointmentservice.pojo;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AppointmentPojo {
    private String ailment;
    private LocalDateTime time;
    @NonNull
    private Integer patId;
    @NonNull
    private Integer docId;
}
