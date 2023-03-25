package org.indexmonitor.user.application.ports.in.user.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.indexmonitor.common.application.models.SelfValidator;

@Getter
@Setter
public class UserProfileUpdateRequest extends SelfValidator<UserProfileUpdateRequest> {
    @NotBlank(message = "User Id can not be empty.")
    private String userId;
    @NotEmpty(message = "Given name field can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 1 and 255 characters.")
    private String givenName;

    @NotEmpty(message = "Family name filed can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 1 and 255 characters.")
    private String familyName;

    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;

    @NotNull(message = "User email confirmed parameter must be defined")
    private Boolean emailConfirmed;
}
