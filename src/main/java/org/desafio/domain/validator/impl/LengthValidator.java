package org.desafio.domain.validator.impl;

import io.quarkus.logging.Log;
import io.quarkus.runtime.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.CustomLength;

public class LengthValidator implements ConstraintValidator<CustomLength, String> {

    private int maxLength;

    private ErrorEnum errorMessage;

    private int minLength;

    @Override
    public void initialize(CustomLength constraintAnnotation) {
        maxLength = constraintAnnotation.value();
        errorMessage = constraintAnnotation.messageEnum();
        minLength = constraintAnnotation.min();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext constraintValidatorContext) {
        Log.info("Validating string length...");
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(errorMessage.getCode())
                .addConstraintViolation();

        constraintValidatorContext.disableDefaultConstraintViolation();
        if(StringUtil.isNullOrEmpty(text)) return true;

        return text.length() <= maxLength && text.length() >= minLength;
    }
}




