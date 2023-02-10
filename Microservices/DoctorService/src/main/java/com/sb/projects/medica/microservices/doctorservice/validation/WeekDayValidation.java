package com.sb.projects.medica.microservices.doctorservice.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WeekDayValidation implements ConstraintValidator<WeekDay, String> {
	@Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final List<String> days = Arrays.asList("monday","tuesday","wednesday","thursday","friday");
        return days.contains(value);
    }

}
