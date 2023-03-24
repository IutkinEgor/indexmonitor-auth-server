package org.indexmonitor.user.application.ports.in.role.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleSettingsLoadRequest extends SelfValidator<RoleSettingsLoadRequest> {
    @NotBlank(message = "Role Id can not be empty.")
    private String id;
}
