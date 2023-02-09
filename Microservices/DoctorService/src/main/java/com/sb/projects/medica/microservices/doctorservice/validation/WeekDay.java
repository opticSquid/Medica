package com.sb.projects.medica.microservices.doctorservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WeekDayValidation.class)
@Documented
public @interface WeekDay {
	String message() default "Name of the days should correspond with name of week days in lowercase ex: monday, tuesday";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
