package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class Doctor {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    protected String name;
    @Email
    protected String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    protected String contactNo;
    private Integer docId;
    @NotNull
    private String regNo;
    @NotNull
    private String degree;
    @NotNull
    private String specialization;
    private String experience;

    public Doctor(String name, String email, String contactNo, String regNo, String degree, String specialization, String experience) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.regNo = regNo;
        this.degree = degree;
        this.specialization = specialization;
        this.experience = experience;
    }

    public void setDocId(@NotNull Integer docId) {
        this.docId = docId;
    }
}
