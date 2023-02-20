package com.sb.projects.medica.microservices.patientservice.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.sb.projects.medica.microservices.patientservice.pojo.PatientPojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name="medical_conditions", joinColumns = @JoinColumn(name="patId"))
    private List<String> medicalConditions = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptionList = new ArrayList<>();

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