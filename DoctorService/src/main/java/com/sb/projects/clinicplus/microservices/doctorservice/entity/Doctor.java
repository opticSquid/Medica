package com.sb.projects.clinicplus.microservices.doctorservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String email;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;

    // TODO: Need to check if by removing the given functions if lombok functions can still do the work
    public int getdId() {
        return dId;
    }

    public String getName() {
        return name;
    }
}
