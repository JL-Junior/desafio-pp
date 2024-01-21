package org.desafio.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.impl.LengthValidator;
import org.desafio.domain.validator.impl.NullOrBlankValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NullOrBlankValidator.class})
public @interface CustomRequiredField {
    ErrorEnum value() default ErrorEnum.DEFAULT_VALIDATION_ERROR;

    String message() default "000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
