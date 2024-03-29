package com.sb.projects.medica.microservices.authenticationservice.controller;

import com.sb.projects.medica.microservices.authenticationservice.pojo.DoctorDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.pojo.PatientDetailsPojo;
import com.sb.projects.medica.microservices.authenticationservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup/patient")
    @CircuitBreaker(name = "patientSignupBreaker", fallbackMethod = "patientSignupFallback")
    @Retry(name = "patientSignupRetry")
    @RateLimiter(name="patientServiceRateLimiter",fallbackMethod = "patientServiceRLExceed")
    public ResponseEntity<String> addNewPatient(@RequestBody @Valid PatientDetailsPojo patientDetails) throws URISyntaxException {
        Integer userId = userService.addNewPatient(patientDetails);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
            return ResponseEntity.created(location).body("new patient user created");
        }
    }

    // CircuitBreaker Fallback method for patient signup method
    public ResponseEntity<String> patientSignupFallback(PatientDetailsPojo patientDetails, Exception ex) {
        log.info("Circuit Breaker Fallback for patient signup is executed because patient service is down: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("Could not sign up patient. Please try again later");
    }
    // Fall back for rate limit exceeded..
    public ResponseEntity<String> patientServiceRLExceed(PatientDetailsPojo patientDetailsPojo, Exception ex){
        log.info("Too many requests rate limit exceeded. Rejecting request: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate Limit exceeded");
    }

    @PostMapping("/signup/doctor")
    @CircuitBreaker(name = "doctorSignupBreaker", fallbackMethod = "doctorSignupFallback")
    @Retry(name = "doctorSignupRetry")
    @RateLimiter(name="doctorServiceRateLimiter",fallbackMethod = "doctorServiceRLExceed")
    public ResponseEntity<String> addNewDoctor(@RequestBody @Valid DoctorDetailsPojo doctorDetails) throws URISyntaxException {
        Integer userId = userService.addNewDoctor(doctorDetails);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
            return ResponseEntity.created(location).body("new doctor user created");
        }
    }

    // Fallback method for doctor signup method
    public ResponseEntity<String> doctorSignupFallback(DoctorDetailsPojo doctorDetails, Exception ex) {
        log.info("Circuit Breaker Fallback for doctor signup is executed because doctor service is down: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("Could not sign up doctor. Please try again later");
    }
    // Fall back for rate limit exceeded..
    public ResponseEntity<String> doctorServiceRLExceed(DoctorDetailsPojo doctorDetails, Exception ex){
        log.info("Too many requests rate limit exceeded. Rejecting request: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate Limit exceeded");
    }

    @DeleteMapping("/delete/{id}")
    @CircuitBreaker(name = "deleteUserBreaker", fallbackMethod = "deleteUserFallback")
    @Retry(name = "deleteUserRetry")
    @RateLimiter(name="deleteUserRateLimiter",fallbackMethod = "deleteUserRLExceed")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) throws URISyntaxException {
        if (Boolean.TRUE.equals(userService.deleteUser(id))) {
            return ResponseEntity.status(HttpStatus.OK).body("user deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user could not be deleted, check for existing associations of this object with other records in database");
        }
    }

    // Fallback method for delete user method
    public ResponseEntity<String> deleteUserFallback(Integer id, Exception ex) {
        String role = userService.findUserRole(id);
        if (Objects.equals(role, "PATIENT")) {
            log.info("Fallback for delete user is executed because patient service is down: {}", ex.getMessage());
            return ResponseEntity.internalServerError().body("Could not delete patient. Please try again later");
        } else {
            log.info("Circuit Breaker Fallback for delete user is executed because doctor service is down: {}", ex.getMessage());
            return ResponseEntity.internalServerError().body("Could not delete doctor. Please try again later");
        }
    }
    // Fall back for rate limit exceeded..
    public ResponseEntity<String> deleteUserRLExceed(Integer id, Exception ex){
        log.info("Too many requests rate limit exceeded. Rejecting request: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate Limit exceeded");
    }
}
