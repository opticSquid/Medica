package com.sb.projects.clinicplus.microservices.appointmentservice.pojo;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
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

    // This constructor is indeed getting called by spring in background when time=null in the constructor
//    public AppointmentPojo(String ailment, LocalDateTime time, @NonNull Integer patId, @NonNull Integer docId) {
//        this.patId = patId;
//        this.docId = docId;
//        this.ailment = ailment;
//        this.time = time == null ? LocalDateTime.now() : time;
//    }

//    public void setAilment(String ailment) {
//        this.ailment = ailment;
//    }
}
