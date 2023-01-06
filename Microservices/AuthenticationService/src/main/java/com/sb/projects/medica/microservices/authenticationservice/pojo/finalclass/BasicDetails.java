package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BasicDetails {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    private String name;
    @Email
    private String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    private String contactNo;
}
