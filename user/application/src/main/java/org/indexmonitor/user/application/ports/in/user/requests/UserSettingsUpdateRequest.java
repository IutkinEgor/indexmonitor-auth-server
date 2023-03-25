package org.indexmonitor.user.application.ports.in.user.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.indexmonitor.common.application.models.SelfValidator;

@Getter
@Setter
public class UserSettingsUpdateRequest extends SelfValidator<UserSettingsUpdateRequest> {
    @NotBlank(message = "User Id can not be empty.")
    private String userId;
    @NotEmpty(message = "UserName field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "UserName can contain latin script and special characters . - _")
    @Size(min = 6, max = 35, message = "Length must be between 6 and 35 characters.")
    private String userName;
    @NotNull(message = "User non expired parameter must be defined")
    private Boolean isUserNonExpired;
    @NotNull(message = "User non locked parameter must be defined")
    private Boolean isUserNonLocked;
    @NotNull(message = "User credentials non expired parameter must be defined")
    private Boolean isCredentialsNonExpired;
    @NotNull(message = "User is enable parameter must be defined")
    private Boolean isEnabled;
}
