package com.sb.projects.medica.microservices.patientservice.pojo;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientPojo {
    @NonNull
    private String name;
    @Email
    private String email;
    @NonNull
    private String contactNo;
    private Integer age;
    private String gender;
    private String medicalConditions;

}
