package com.sb.projects.medica.microservices.authenticationservice.pojo;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewUser extends UserPojo{
    @NotNull
    @Size(min=8, message = "Password must be 8 characters long")
    private String password;
}
