package com.sb.projects.medica.microservices.appointmentservice.pojo;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @NonNull
    private Integer pId;
    private String name;
    private String email;
    private String contactNo;
}
