package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import com.sb.projects.medica.microservices.authenticationservice.validation.GenderType;
import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Patient extends BasicDetails {
    @Min(0)
    private Integer age;
    @GenderType
    private String gender;
    private String medicalConditions;

    public Patient(String name, String email, String contactNo, Integer age, String gender, String medicalConditions) {
        super(name, email, contactNo);
        this.age = age;
        this.gender = gender;
        this.medicalConditions = medicalConditions;
    }
}
