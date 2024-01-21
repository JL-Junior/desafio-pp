package org.desafio.domain.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.CustomMin;

import java.util.Objects;

public class DoubleMinValidator implements ConstraintValidator<CustomMin, Double> {

    private Double minAmount;

    private ErrorEnum errorMessage;

    @Override
    public void initialize(CustomMin constraintAnnotation) {
        minAmount = constraintAnnotation.value();
        errorMessage = constraintAnnotation.messageEnum();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double amount, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(errorMessage.getCode())
                .addConstraintViolation();

        constraintValidatorContext.disableDefaultConstraintViolation();

        if(Objects.isNull(amount)) return true;

        return amount >= minAmount;
    }
}
