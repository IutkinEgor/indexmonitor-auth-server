package org.indexmonitor.user.application.ports.in.authority.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthoritySettingsUpdateRequest extends SelfValidator<AuthoritySettingsUpdateRequest> {
    @NotBlank(message = "Authority Id can not be empty.")
    private String id;
    @NotBlank(message = "Authority name can not be empty.")
    private String name;
    private String description;
    @NotNull(message = "Authority isEnable property must be defined.")
    private Boolean isEnable;
    @NotNull(message = "Authority isObtainable property must be defined.")
    private Boolean isObtainable;
}
