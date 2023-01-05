package com.sb.projects.medica.microservices.authenticationservice.entity;

import com.sb.projects.medica.microservices.authenticationservice.pojo.BasicDetailsPojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String name;
    private String email;
    private String contactNo;
    private String role;
    private String password;

    public User(BasicDetailsPojo basicDetailsPojo){
        this.name=basicDetailsPojo.getName();
        this.email=basicDetailsPojo.getEmail();
        this.contactNo= basicDetailsPojo.getContactNo();
        this.role= basicDetailsPojo.getRole();
        this.password = basicDetailsPojo.getPassword();
    }
}
