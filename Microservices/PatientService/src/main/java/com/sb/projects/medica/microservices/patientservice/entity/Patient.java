package com.sb.projects.medica.microservices.patientservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.sb.projects.medica.microservices.patientservice.pojo.PatientPojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private Integer patId;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String contactNo;
    private Integer age;
    private String gender;
    private String medicalConditions;

    public Patient(PatientPojo patient) {
        this.patId = patient.getPatId();
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.contactNo = patient.getContactNo();
        this.age = patient.getAge();
        this.gender = patient.getGender();
        this.medicalConditions = patient.getMedicalConditions();
    }
}
