package com.sb.projects.medica.microservices.authenticationservice.entity;

import com.sb.projects.medica.microservices.authenticationservice.pojo.UserPojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;
    @Size(min = 5, message = "Name should be at least 5 characters long")
    private String name;
    @Email
    private String email;
    private String contactNo;
    @NotNull
    private String role;
    @NotNull
    @Size(min=8, message = "Password must be 8 characters long")
    private String password;

    public User(UserPojo userPojo){
        this.name = userPojo.getName();
        this.email = userPojo.getEmail();
        this.contactNo = userPojo.getContactNo();
        this.role = userPojo.getRole();
    }
}
