package org.indexmonitor.client.application.ports.in.client.requests;

import org.indexmonitor.client.domain.enums.AuthenticationMethod;
import org.indexmonitor.client.domain.enums.OAuthGrantType;
import org.indexmonitor.common.application.exception.AppValidationException;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.*;

import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Getter
public class ClientRegisterRequest extends SelfValidator<ClientRegisterRequest> {

    @NotBlank(message = "Client Id can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "Client Id can contain only latin script and special characters . - _")
    @Size(min = 6, max = 35, message = "Client Id length must be between 6 and 35 characters.")
    private String clientId;
    @NotNull(message = "Client Id issued timestamp can not be empty.")
    private final Instant clientIdIssuedAt;

   // @NotBlank(message = "Client secrete can not be empty.")
    @Size(min = 8, max = 255, message = "Client secrete length must be between 8 and 255 characters.")
    private String secret;

    @NotBlank(message = "Client Name can not be empty.")
    @Size(min = 6, max = 255, message = "Length must be between 6 and 255 characters.")
    private String name;

    //@NotBlank(message = "Client description can not be empty.")
    //@Size(min = 8, max = 255, message = "Client description length must be between 8 and 255 characters.")
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

    //@NotNull(message = "Client scope can not be empty.")
    private Set<String> scopes;

    public ClientRegisterRequest() {
        this.clientIdIssuedAt = Instant.now();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

//    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
//        this.clientSecretExpiresAt = clientSecretExpiresAt;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthenticationMethods(Set<String> authenticationMethods) {
        this.authenticationMethods = authenticationMethods.stream().map(method -> method.toUpperCase()).collect(Collectors.toSet());
    }

    public void setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes.stream().map(type -> type.toUpperCase()).collect(Collectors.toSet());
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public void validateSelf(){
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<ClientRegisterRequest>> constraintViolations =  super.getObjectConstraints(this);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
      //  clientSecretExpiresAtValidation(errors);
        authenticationMethodsValidation(errors);
        secreteValidation(errors);
        authorizationGrantTypesValidation(errors);
        originValidation(errors);
        redirectUrlsValidation(errors);
        scopesValidation(errors);
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

    private Set<String> secreteValidation(Set<String> errors){
        if(this.authenticationMethods != null
                && this.authenticationMethods.contains(AuthenticationMethod.CLIENT_SECRET_BASIC.getMethod().toUpperCase())
                && this.secret == null){
            errors.add("Selected authentication method: CLIENT_SECRET_BASIC. Secret can not be NULL.");
        }
        return errors;
    }

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

    private Set<String> scopesValidation(Set<String> errors){
        if(scopes == null) return errors;
        String scopeRegex = "[A-Za-z0-9\\-_. ]";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(scopeRegex);
        scopes.forEach(scope -> {
            Matcher matcher = pattern.matcher(scope);
            if(!matcher.find()) {
                errors.add("Scope has an invalid format. Allowed only latin script, special characters . - _ and numbers. Value: " + scope);
            }
        });
        return errors;
    }
}
