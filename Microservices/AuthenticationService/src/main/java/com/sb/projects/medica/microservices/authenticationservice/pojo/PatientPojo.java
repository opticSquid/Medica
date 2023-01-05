package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.sb.projects.medica.microservices.authenticationservice.validation.GenderType;
import lombok.*;

import javax.validation.constraints.Min;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientPojo extends BasicDetailsPojo {
    @Min(0)
    private Integer age;
    @GenderType
    private String gender;
    private String medicalConditions;
}
