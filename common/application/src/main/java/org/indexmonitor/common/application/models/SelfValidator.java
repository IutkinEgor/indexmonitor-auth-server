package org.indexmonitor.common.application.models;

import org.indexmonitor.common.application.exception.AppConstraintValidationException;
import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidator<T> {

    public void validateSelf() {
        Set<ConstraintViolation<T>> violations = getObjectConstraints();
        if (!violations.isEmpty()) {
            throw new AppConstraintValidationException(violations);
        }
    }
    public Set<ConstraintViolation<T>> getObjectConstraints(){
        return Validation.buildDefaultValidatorFactory().getValidator().validate((T) this);
    }

    public Set<ConstraintViolation<T>> getObjectConstraints(T data){
        return Validation.buildDefaultValidatorFactory().getValidator().validate(data);
    }

    public Set<ConstraintViolation<T>> getFieldConstrains(String filedName){
        return Validation.buildDefaultValidatorFactory().getValidator().validateProperty((T) this, filedName);
    }

    public boolean isValid(){
        return Validation.buildDefaultValidatorFactory().getValidator().validate((T) this).isEmpty();
    }
}
