package com.sb.projects.medica.microservices.authenticationservice.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewUserDetails {
    @JsonProperty(value = "basicDetails")
    private BasicDetailsPojo basicDetailsPojo;
    @JsonProperty(value = "doctorDetails")
    private DoctorPojo doctorPojo;
    @JsonProperty(value = "patientDetails")
    private PatientPojo patientPojo;

    // For a doctor User
    public NewUserDetails(BasicDetailsPojo basicDetailsPojo, DoctorPojo doctorPojo) {
        this.basicDetailsPojo = basicDetailsPojo;
        this.doctorPojo = doctorPojo;
    }

    // For a patient User
    public NewUserDetails(BasicDetailsPojo basicDetailsPojo, PatientPojo patientPojo) {
        this.basicDetailsPojo = basicDetailsPojo;
        this.patientPojo = patientPojo;
    }
}
