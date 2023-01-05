package com.sb.projects.medica.microservices.authenticationservice.service;

import com.sb.projects.medica.microservices.authenticationservice.entity.User;
import com.sb.projects.medica.microservices.authenticationservice.pojo.NewUserDetails;
import com.sb.projects.medica.microservices.authenticationservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //TODO: based on the userRole, call the specific microservice and add a new User
    public Integer addNewUser(NewUserDetails newUserDetails) {
        try {
            log.debug("Incoming user to be added: {}", newUserDetails);
            User tobeSaved = new User(newUserDetails.getBasicDetailsPojo());
            userRepo.save(tobeSaved);
            return tobeSaved.getUserID();
        } catch (IllegalStateException ex) {
            log.debug("Failed to save the entry to database: {}", ex.toString());
            return null;
        }
    }

}
