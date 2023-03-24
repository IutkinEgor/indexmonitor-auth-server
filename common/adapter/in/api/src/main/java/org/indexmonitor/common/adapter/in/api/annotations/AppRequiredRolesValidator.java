package org.indexmonitor.common.adapter.in.api.annotations;

import org.indexmonitor.common.adapter.in.api.exceptions.OAuthSubException;
import org.indexmonitor.common.adapter.in.api.utils.OAuthExtractUtil;
import org.indexmonitor.common.application.exception.AppAnnotationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class AppRequiredRolesValidator implements ConstraintValidator<AppRequiredRoles,Object> {

    private String[] value;
    private String equalsTo;
    private String message;

    @Override
    public void initialize(AppRequiredRoles constraintAnnotation) {
        this.value = constraintAnnotation.value();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var a = OAuthExtractUtil.getRoles();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new OAuthSubException("Not authorized");
        }
        if(!(authentication instanceof JwtAuthenticationToken)) {
            throw new OAuthSubException("Authentication is not JwtAuthenticationToken instance");
        }
        Jwt jwt = (Jwt)authentication.getPrincipal();
        Object roleObject = jwt.getClaims().get("roles");
        if(roleObject == null || !(roleObject instanceof List<?>)) {
            throw new OAuthSubException("No roles");
        }
        List<String> roles = (List<String>) roleObject;
        Boolean containrole = roles.containsAll(Arrays.asList(value));

        return roles.containsAll(Arrays.asList(value));
    }

    private Object getProperty(Object value, String fieldName) throws AppAnnotationException {
        Class<?> clazz = value.getClass();
        String methodName = "get" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            return method.invoke(value);
        } catch (Exception e) {
            String message = String.format("Annotation: '" + AppRequiredRoles.class.getName() + "'.Method: '" + methodName + "' NOT FOUND or it value can't be invoke.");
            throw new AppAnnotationException(message);
        }
    }


}
