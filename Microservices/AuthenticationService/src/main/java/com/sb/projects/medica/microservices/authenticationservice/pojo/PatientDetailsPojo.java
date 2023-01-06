package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.Patient;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientDetailsPojo extends Patient {
    //TODO: Statically filtering this field to avoid sending it on any response
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;

    public PatientDetailsPojo(String name, String email, String contactNo, Integer age, String gender, String medicalConditions,String password){
        super(name, email, contactNo, age, gender, medicalConditions);
        this.password = password;
    }
}
