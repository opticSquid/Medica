package com.sb.projects.medica.microservices.doctorservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Integer dId;
    @Size(min = 6, message = "Name should be at least 6 characters long")
    private String name;
    @Email
    private String email;
    private String contactNumber;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;
    private String experience;
}
