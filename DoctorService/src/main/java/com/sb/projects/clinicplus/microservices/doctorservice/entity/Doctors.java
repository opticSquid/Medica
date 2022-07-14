package com.sb.projects.clinicplus.microservices.doctorservice.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int d_id;
    @Size(min = 6, message = "Name should be at least 6 characters long")
    private String name;
    private String email;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;

    public int getD_id() {
        return d_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "d_id=" + d_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", degree='" + degree + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
