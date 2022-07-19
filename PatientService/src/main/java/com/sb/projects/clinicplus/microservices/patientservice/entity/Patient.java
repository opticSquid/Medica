package com.sb.projects.clinicplus.microservices.patientservice.entity;

import com.sun.istack.NotNull;
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
    @NotNull
    private int p_id;
    private String name;
    private String email;
    private String contact_no;
    // TODO: Need to check if by removing the given functions if lombok functions can still do the work
    public int getP_id() {
        return p_id;
    }
    public String getName() {
        return name;
    }
}
