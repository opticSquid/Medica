package com.sb.projects.medica.microservices.authenticationservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderTypeValidation implements ConstraintValidator<GenderType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final List<String> roleType = Arrays.asList("MALE", "FEMALE", "OTHERS");
        return roleType.contains(value);
    }
}
