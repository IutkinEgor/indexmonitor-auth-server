package org.indexmonitor.common.application.exception;

import org.indexmonitor.common.domain.exceptions.ApplicationException;
import jakarta.validation.ConstraintViolation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class AppConstraintValidationException extends ApplicationException {
    private final static String MESSAGE = "Validation error.";
    private final Set<ConstraintViolation<?>> constraintViolations;

    public AppConstraintValidationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(MESSAGE);
        if ( constraintViolations == null ) {
            this.constraintViolations = null;
        }
        else {
            this.constraintViolations = new HashSet<>( constraintViolations );
        }
    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map( cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage() )
                .collect( Collectors.joining( ", " ) );
    }

    public Set<String> getErrors(){
        Set<String> errors = new HashSet<>();
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        return errors;
    }

}
