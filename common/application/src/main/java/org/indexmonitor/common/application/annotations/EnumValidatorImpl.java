package org.indexmonitor.common.application.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {

    List<String> valueList = null;
    private String message;
    private String filedName;


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //   context.disableDefaultConstraintViolation();
        //   context.buildConstraintViolationWithTemplate( this.message ).addPropertyNode(field).addConstraintViolation();
        return valueList.contains(String.valueOf(value).toUpperCase());
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {

        valueList = new ArrayList<String>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }
    }

}