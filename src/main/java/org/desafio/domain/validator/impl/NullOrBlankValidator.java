package org.desafio.domain.validator.impl;

import io.quarkus.runtime.util.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.CustomLength;
import org.desafio.domain.validator.CustomRequiredField;

import java.util.Objects;

public class NullOrBlankValidator implements ConstraintValidator<CustomRequiredField, Object> {

        private ErrorEnum errorMessage;

        @Override
        public void initialize(CustomRequiredField constraintAnnotation) {
            errorMessage = constraintAnnotation.value();

            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(errorMessage.getCode())
                    .addConstraintViolation();

            constraintValidatorContext.disableDefaultConstraintViolation();

            if(Objects.isNull(obj)) return false;

            if(obj instanceof String) return StringUtil.isNullOrEmpty((String)obj);
            return true;
        }
}
