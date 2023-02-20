package com.sb.projects.medica.microservices.patientservice.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientPojo {
    @NotNull
    private Integer patId;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String contactNo;
    @Min(0)
    private Integer age;
    private String gender;
    private List<String> medicalConditions;
    private List<PrescriptionPOJO> prescriptions;

}