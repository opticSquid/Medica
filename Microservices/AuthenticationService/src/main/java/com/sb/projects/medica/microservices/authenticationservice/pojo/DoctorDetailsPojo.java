package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Doctor;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DoctorDetailsPojo extends Doctor {
    //TODO: Statically filtering this field to avoid sending it on any response
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;

    public DoctorDetailsPojo(String name, String email, String contactNo, String regNo, String degree, String specialization, String experience, String password){
        super(name, email, contactNo, regNo, degree, specialization, experience);
        this.password = password;
    }

}
