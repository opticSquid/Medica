package com.sb.projects.medica.microservices.authenticationservice.Controller;

import com.sb.projects.medica.microservices.authenticationservice.pojo.DoctorPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.UserPojo;
import com.sb.projects.medica.microservices.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/authenticate")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
}
