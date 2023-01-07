package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import com.sb.projects.medica.microservices.authenticationservice.validation.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class Patient {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    protected String name;
    @Email
    protected String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    protected String contactNo;
    private Integer patId;
    @Min(0)
    private Integer age;
    @GenderType
    private String gender;
    private String medicalConditions;

    public Patient(String name, String email, String contactNo, Integer age, String gender, String medicalConditions) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.age = age;
        this.gender = gender;
        this.medicalConditions = medicalConditions;
    }

    public void setPatId(@NotNull Integer patId) {
        this.patId = patId;
    }
}
