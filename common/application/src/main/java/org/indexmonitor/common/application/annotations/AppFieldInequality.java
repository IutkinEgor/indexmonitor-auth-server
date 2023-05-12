package org.indexmonitor.common.application.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AppFieldInequalityValidator.class)
public @interface AppFieldInequality {
    String message() default "Fields are equal.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String notEqualsTo();
}
