package com.sb.projects.medica.microservices.authenticationservice.controller;

import com.sb.projects.medica.microservices.authenticationservice.pojo.NewUserDetails;
import com.sb.projects.medica.microservices.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody NewUserDetails newUserDetails) {
        log.info("Adding new User: {}", newUserDetails);
        Integer userId = userService.addNewUser(newUserDetails);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
            return ResponseEntity.created(location).build();
        }
    }
}
