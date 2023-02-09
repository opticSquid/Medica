package com.sb.projects.medica.microservices.doctorservice.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPOJO {
//	private Integer docId;
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
//	private List<Timing> slots;
}
