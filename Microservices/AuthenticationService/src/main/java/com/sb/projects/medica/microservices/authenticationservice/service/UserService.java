package com.sb.projects.medica.microservices.authenticationservice.service;

import com.sb.projects.medica.microservices.authenticationservice.entity.User;
import com.sb.projects.medica.microservices.authenticationservice.pojo.UserPojo;
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
    public User addNewUser(UserPojo userPojo) {
        log.debug("Incoming user to be added: {}", userPojo);
        try {
            User user = new User(userPojo);
            userRepo.save(new User(userPojo));
            return user;
        } catch (IllegalStateException ex) {
            return null;
        }
    }

}
