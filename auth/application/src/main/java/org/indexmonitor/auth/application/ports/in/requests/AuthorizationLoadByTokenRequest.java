package org.indexmonitor.auth.application.ports.in.requests;

import jakarta.validation.constraints.NotBlank;
import org.indexmonitor.common.application.models.SelfValidator;

public class AuthorizationLoadByTokenRequest extends SelfValidator<AuthorizationLoadByTokenRequest> {
    @NotBlank(message = "Authorization token value can not be empty")
    private String token;
}
