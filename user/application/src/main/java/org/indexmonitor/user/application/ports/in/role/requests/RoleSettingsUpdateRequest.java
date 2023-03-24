package org.indexmonitor.user.application.ports.in.role.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleSettingsUpdateRequest extends SelfValidator<RoleSettingsUpdateRequest> {
    @NotBlank(message = "Role record Id can not be empty.")
    private String id;
    @NotBlank(message = "Role name can not be empty.")
    private String name;
    private String description;
    @NotNull(message = "Role isEnable property must be defined.")
    private Boolean isEnable;
    @NotNull(message = "Role isObtainable property must be defined.")
    private Boolean isObtainable;
}
