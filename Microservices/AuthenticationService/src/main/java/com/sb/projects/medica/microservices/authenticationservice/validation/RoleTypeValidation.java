package com.sb.projects.medica.microservices.authenticationservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RoleTypeValidation implements ConstraintValidator<RoleType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final List<String> roleType = Arrays.asList("USER", "DOCTOR");
        return roleType.contains(value);
    }
}
