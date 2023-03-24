package org.indexmonitor.common.application.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AppFieldEqualityValidator.class)
public @interface AppFieldEquality {

    String message() default "Fields are not equal.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String equalsTo();
}
