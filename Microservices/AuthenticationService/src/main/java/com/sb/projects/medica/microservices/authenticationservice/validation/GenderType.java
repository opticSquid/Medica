package com.sb.projects.medica.microservices.authenticationservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderTypeValidation.class)
@Documented
public @interface GenderType {
    String message() default "Gender preference must be MALE, FEMALE or OTHERS.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
