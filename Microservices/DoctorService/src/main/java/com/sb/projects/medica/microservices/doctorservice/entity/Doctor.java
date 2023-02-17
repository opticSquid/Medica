package com.sb.projects.medica.microservices.doctorservice.entity;

import com.sb.projects.medica.microservices.doctorservice.pojo.DoctorPOJO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Doctor {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotNull
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
    @OneToMany(mappedBy = "doctor")
    private final List<Slot> slots = new ArrayList<>();

    public Doctor(DoctorPOJO doctor) {
        this.docId = doctor.getDocId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.contactNo = doctor.getContactNo();
        this.regNo = doctor.getContactNo();
        this.degree = doctor.getDegree();
        this.specialization = doctor.getSpecialization();
        this.experience = doctor.getExperience();
    }

    public Doctor(Integer docId, String name, String email, String contactNo, String regNo, String degree, String specialization, String experience) {
        this.docId = docId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.regNo = regNo;
        this.degree = degree;
        this.specialization = specialization;
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public void removeSlot(Slot slot) {
        slots.remove(slot);
    }

}
