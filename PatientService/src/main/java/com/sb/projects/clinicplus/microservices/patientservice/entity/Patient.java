package com.sb.projects.clinicplus.microservices.patientservice.entity;

import com.sb.projects.clinicplus.microservices.patientservice.pojo.PatientPojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String email;
    @NonNull
    private String contactNo;

    public Patient(PatientPojo patient) {
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.contactNo = patient.getContactNo();
    }
}
