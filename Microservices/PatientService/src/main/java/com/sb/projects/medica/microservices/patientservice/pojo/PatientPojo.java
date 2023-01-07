package com.sb.projects.medica.microservices.patientservice.pojo;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientPojo {
    @NotNull
    private Integer patId;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String contactNo;
    @Min(0)
    private Integer age;
    private String gender;
    private String medicalConditions;

}
