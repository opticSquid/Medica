package com.sb.projects.medica.microservices.doctorservice.pojo;

import com.sb.projects.medica.microservices.doctorservice.entity.Timing;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPOJO {
    @NotNull
    private Integer docId;
    @Size(min = 6, message = "Name should be at least 6 characters long")
    private String name;
    @Email
    private String email;
    @Size(min = 10, message = "Contact number must be of 10 digits")
    private String contactNo;
    @NotNull
    private String regNo;
    @NotNull
    private String degree;
    @NotBlank
    private String specialization;
    private String experience;

}
