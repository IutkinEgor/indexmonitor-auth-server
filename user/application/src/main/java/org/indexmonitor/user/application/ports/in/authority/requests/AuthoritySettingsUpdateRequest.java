package org.indexmonitor.user.application.ports.in.authority.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Authority name field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "Authority name can contain latin script, special characters . - _ and numbers.")
    @Size(min = 4, max = 35, message = "Authority name length must be between 6 and 35 characters.")
    private String name;
    private String description;
    @NotNull(message = "Authority isEnable property must be defined.")
    private Boolean isEnable;
    @NotNull(message = "Authority isObtainable property must be defined.")
    private Boolean isObtainable;
}
