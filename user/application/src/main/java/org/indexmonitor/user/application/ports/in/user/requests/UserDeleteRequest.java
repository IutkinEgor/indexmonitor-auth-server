package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeleteRequest extends SelfValidator<UserDeleteRequest> {
    @NotBlank(message = "Client record Id can not be empty.")
    private String id;
}
