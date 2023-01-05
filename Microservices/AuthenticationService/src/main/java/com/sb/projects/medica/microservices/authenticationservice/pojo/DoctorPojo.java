package com.sb.projects.medica.microservices.authenticationservice.pojo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPojo extends BasicDetailsPojo {
    @NotNull
    private String regNo;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;
    private String experience;
}
