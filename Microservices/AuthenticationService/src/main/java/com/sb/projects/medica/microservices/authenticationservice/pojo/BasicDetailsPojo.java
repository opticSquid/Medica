package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass.BasicDetails;
import com.sb.projects.medica.microservices.authenticationservice.validation.RoleType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasicDetailsPojo extends BasicDetails {
    //TODO: Statically filtering this field to avoid sending it on any response
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;
    @RoleType
    private String role;
    public BasicDetailsPojo(String name, String email, String contactNo, String password, String role){
        super(name, email, contactNo);
        this.password = password;
        this.role = role;
    }

}
