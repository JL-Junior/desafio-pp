package org.desafio.domain.validator.impl;

import io.quarkus.runtime.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.CustomEmailPattern;

import java.util.Objects;

public class CustomEmailValidator implements ConstraintValidator<CustomEmailPattern, String> {
    ErrorEnum errorEnum;

    @Override
    public void initialize(CustomEmailPattern constraintAnnotation) {
        errorEnum = constraintAnnotation.value();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(errorEnum.getCode())
                .addConstraintViolation();

        constraintValidatorContext.disableDefaultConstraintViolation();
        if(StringUtil.isNullOrEmpty(s)) return true;
        return s.matches("^[a-z0-9\\_.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
    }
}
