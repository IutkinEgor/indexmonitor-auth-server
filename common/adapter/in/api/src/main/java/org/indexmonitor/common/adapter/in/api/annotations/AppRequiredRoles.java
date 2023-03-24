package org.indexmonitor.common.adapter.in.api.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppRequiredRolesValidator.class)
public @interface AppRequiredRoles {

    String message() default "Fields are not equal.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] value();
}
