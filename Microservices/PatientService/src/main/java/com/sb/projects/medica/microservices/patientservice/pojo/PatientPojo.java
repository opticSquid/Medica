package com.sb.projects.medica.microservices.patientservice.pojo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientPojo {
    @NonNull
    private String name;
    private String email;
    @NonNull
    private String contactNo;
}
