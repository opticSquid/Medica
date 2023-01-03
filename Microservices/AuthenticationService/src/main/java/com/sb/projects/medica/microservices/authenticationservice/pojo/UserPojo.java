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
public class UserPojo {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    private String name;
    @Email
    private String email;
    private String contactNo;
    @RoleType
    private String role;

}
