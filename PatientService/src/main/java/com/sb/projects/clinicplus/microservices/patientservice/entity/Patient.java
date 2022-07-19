package com.sb.projects.clinicplus.microservices.patientservice.entity;

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
    @NonNull
    private Integer pId;
    private String name;
    private String email;
    private String contactNo;

    // TODO: Need to check if by removing the given functions if lombok functions can still do the work
    public int getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }
}
