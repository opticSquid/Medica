package com.sb.projects.medica.microservices.appointmentservice.pojo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @NonNull
    private Integer dId;
    @Size(min = 6, message = "Name should be at least 6 characters long")
    private String name;
    private String email;
    @NotBlank
    private String degree;
    @NotBlank
    private String specialization;
}
