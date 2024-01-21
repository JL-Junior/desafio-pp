package org.desafio.domain.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.impl.DoubleMinValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DoubleMinValidator.class})
public @interface CustomMin {
    ErrorEnum messageEnum() default ErrorEnum.DEFAULT_VALIDATION_ERROR;

    double value();

    String message() default "000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
