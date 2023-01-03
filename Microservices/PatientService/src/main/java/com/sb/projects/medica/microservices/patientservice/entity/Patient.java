package com.sb.projects.medica.microservices.patientservice.entity;

import com.sb.projects.medica.microservices.patientservice.pojo.PatientPojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pId;
    @NonNull
    private String name;
    @Email
    private String email;
    @NonNull
    private String contactNo;
      private Integer age;
    private String gender;
    private String medicalConditions;

    public Patient(PatientPojo patient) {
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.contactNo = patient.getContactNo();
        this.age = patient.getAge();
        this.gender = patient.getGender();
        this.medicalConditions = patient.getMedicalConditions();
    }
}
