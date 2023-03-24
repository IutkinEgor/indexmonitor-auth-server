package org.indexmonitor.user.application.ports.in.role.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoleDeleteRequest extends SelfValidator<RoleDeleteRequest> {
    @NotBlank(message = "Role Id can not be empty.")
    private String id;
}
