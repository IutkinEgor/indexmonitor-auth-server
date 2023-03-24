package org.indexmonitor.client.application.ports.in.client.requests;

import org.indexmonitor.client.domain.enums.AuthenticationMethod;
import org.indexmonitor.client.domain.enums.OAuthGrantType;
import org.indexmonitor.common.application.exception.AppValidationException;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ClientUpdateRequest extends SelfValidator<ClientUpdateRequest> {

    @NotBlank(message = "Client record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;

    @NotBlank(message = "Client Id can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "Client Id can contain only latin script and special characters . - _")
    @Size(min = 6, max = 35, message = "Client Id length must be between 6 and 35 characters.")
    private String clientId;

    @NotBlank(message = "Client Name can not be empty.")
    @Size(min = 6, max = 255, message = "Length must be between 6 and 255 characters.")
    private String name;

    private String description;

    @NotNull(message = "Client authentication method can not be empty.")
    @NotEmpty(message = "Client authentication method can not be empty.")
    private Set<String> authenticationMethods;

    @NotEmpty(message = "Client authorization grant type can not be empty.")
    private Set<String> authorizationGrantTypes;

    @NotBlank(message = "Client origin can not be empty.")
    private String origin;

    @NotNull(message = "Client redirect uri can not be empty.")
    private Set<String> redirectUris;

    @NotNull(message = "requireProofKey must be defined")
    private Boolean requireProofKey;

    @NotNull(message = "requireAuthorizationConsent must be defined")
    private Boolean requireAuthorizationConsent;

    public java.util.UUID getId() {
        return java.util.UUID.fromString(id);
    }

    @Override
    public void validateSelf(){
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<ClientUpdateRequest>> constraintViolations =  super.getObjectConstraints(this);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        authenticationMethodsValidation(errors);
        //secreteValidation(errors);
        authorizationGrantTypesValidation(errors);
        originValidation(errors);
        redirectUrlsValidation(errors);
        if(!errors.isEmpty()){
            throw new AppValidationException(errors);
        }
    }


    private Set<String> authenticationMethodsValidation(Set<String> errors){
        this.authenticationMethods.forEach(method -> {
            try {
                AuthenticationMethod.valueOf(method);
            } catch (IllegalArgumentException e) {
                errors.add("Authentication method is not allowed: " + method);
            }
        });
        return errors;
    }

//    private Set<String> secreteValidation(Set<String> errors){
//        if(this.authenticationMethods != null
//                && this.authenticationMethods.contains(AuthenticationMethod.CLIENT_SECRET_BASIC.getMethod().toUpperCase())
//                && this.secret == null){
//            errors.add("Selected authentication method: CLIENT_SECRET_BASIC. Secret can not be NULL.");
//        }
//        return errors;
//    }

    private Set<String> authorizationGrantTypesValidation(Set<String> errors){
        this.authorizationGrantTypes.forEach(method -> {
            try {
                OAuthGrantType.valueOf(method);
            } catch (IllegalArgumentException e) {
                errors.add("Authentication method is not allowed: " + method);
            }
        });
        return errors;
    }

    private Set<String> originValidation(Set<String> errors){
        try {
            new URL(origin);
        } catch (MalformedURLException e) {
            errors.add("Origin url has an invalid url format: " + origin);
        }
        return errors;
    }

    private Set<String> redirectUrlsValidation(Set<String> errors){
        redirectUris.forEach(redirectUri -> {
            try {
                new URL(redirectUri);
            } catch (MalformedURLException e) {
                errors.add("Redirect url has an invalid url format: " + redirectUri);
            }
        });
        return errors;
    }
}
