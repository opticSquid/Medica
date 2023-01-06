package com.sb.projects.medica.microservices.authenticationservice.pojo.finalclass;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@ToString
@NoArgsConstructor
public class Doctor extends BasicDetails {
    private Integer docId;
    @NotNull
    private String regNo;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;
    private String experience;
    public Doctor(String name, String email, String contactNo, String regNo, String degree, String specialization, String experience){
        super(name, email, contactNo);
        this.regNo = regNo;
        this.degree = degree;
        this.specialization = specialization;
        this.experience = experience;
    }

    public void setDocId(@NotNull Integer docId) {
        this.docId = docId;
    }
}
