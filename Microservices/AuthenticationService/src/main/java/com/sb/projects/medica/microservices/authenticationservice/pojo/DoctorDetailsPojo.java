package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Doctor;
import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Slot;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DoctorDetailsPojo extends Doctor {
    //TODO: Statically filtering this field to avoid sending it on any response
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;

    // if slots are not provided
    public DoctorDetailsPojo(String name, String email, String contactNo, String regNo, String degree, String specialization, String experience, String password) {
        super(name, email, contactNo, regNo, degree, specialization, experience);
        this.password = password;
    }

    // if slots are provided
    public DoctorDetailsPojo(String name, String email, String contactNo, String regNo, String degree, String specialization, String experience, List<Slot> slots, String password) {
        super(name, email, contactNo, regNo, degree, specialization, experience, slots);
        this.password = password;
    }

}
