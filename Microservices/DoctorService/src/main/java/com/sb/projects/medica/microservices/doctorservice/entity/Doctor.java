package com.sb.projects.medica.microservices.doctorservice.entity;

import com.sb.projects.medica.microservices.doctorservice.pojo.DoctorPOJO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @NotNull
    private Integer docId;
    @Size(min = 6, message = "Name should be at least 6 characters long")
    private String name;
    @Email
    private String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    private String contactNo;
    @NotNull
    private String regNo;
    @NotNull
    private String degree;
    @NotBlank
    private String specialization;
    private String experience;
    @OneToMany
    private List<Timing> slots;
    public Doctor(DoctorPOJO doctor){
        this.docId = doctor.getDocId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.contactNo = doctor.getContactNo();
        this.regNo = doctor.getContactNo();
        this.degree = doctor.getDegree();
        this.specialization = doctor.getSpecialization();
        this.experience = doctor.getExperience();
    }
}
