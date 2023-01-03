package com.sb.projects.medica.microservices.authenticationservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleTypeValidation.class)
@Documented
public @interface RoleType {
    String message() default "Role preference must be USER or DOCTOR.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
