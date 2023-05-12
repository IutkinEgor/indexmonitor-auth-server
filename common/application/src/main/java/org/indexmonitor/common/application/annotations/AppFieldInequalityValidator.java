package org.indexmonitor.common.application.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.indexmonitor.common.application.exception.AppAnnotationException;

import java.lang.reflect.Method;

public class AppFieldInequalityValidator implements ConstraintValidator<AppFieldInequality,Object> {
    private final String METHOD_NOTFOUND = String.format("Annotation name: " + AppFieldInequality.class.getName() + "");

    private String field;
    private String notEqualsTo;
    private String message;

    @Override
    public void initialize(AppFieldInequality constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.notEqualsTo = constraintAnnotation.notEqualsTo();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object fieldObject = getProperty(value, field);
            final Object equalsToObject = getProperty(value, notEqualsTo);

            if (fieldObject == null && equalsToObject == null) {
                return true;
            }

            boolean matches = (fieldObject != null)
                    && !fieldObject.equals(equalsToObject);

            if (!matches) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate( this.message ).addPropertyNode(field).addConstraintViolation();
            }

            return matches;
        } catch (final Exception e) {

        }
        return true;
    }

    private Object getProperty(Object value, String fieldName) throws AppAnnotationException {
        Class<?> clazz = value.getClass();
        String methodName = "get" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            return method.invoke(value);
        } catch (Exception e) {
            String message = String.format("Annotation: '" + AppFieldInequality.class.getName() + "'.Method: '" + methodName + "' NOT FOUND or it value can't be invoke.");
            throw new AppAnnotationException(message);
        }
    }
}
