package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Getter
@ToString
public abstract class BasicDetails {
    @Size(min = 5, message = "Name should be at least 5 characters long")
    protected String name;
    @Email
    protected String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    protected String contactNo;
}
