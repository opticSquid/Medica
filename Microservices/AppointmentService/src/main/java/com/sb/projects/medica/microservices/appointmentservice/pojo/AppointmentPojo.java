package com.sb.projects.medica.microservices.appointmentservice.pojo;

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
