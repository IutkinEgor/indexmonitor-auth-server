package org.indexmonitor.auth.application.ports.in.requests;

import jakarta.validation.constraints.NotBlank;
import org.indexmonitor.common.application.models.SelfValidator;

public class AuthorizationLoadByIdRequest extends SelfValidator<AuthorizationLoadByIdRequest> {
    @NotBlank(message = "Authorization Id can not be empty")
    private String id;
}
