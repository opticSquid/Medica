package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.validation.RoleType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BasicDetailsPojo {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    private String name;
    @Email
    private String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    private String contactNo;
    @RoleType
    private String role;
    //TODO: Statically filtering this field to avoid sending it on any response
    @NotNull
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;
}
