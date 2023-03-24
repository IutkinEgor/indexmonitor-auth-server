package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.annotations.AppFieldEquality;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@AppFieldEquality(field = "confirmPassword", equalsTo = "password", message = "The password and confirm password do not match.")
public class UserRegisterManualRequest extends SelfValidator<UserRegisterManualRequest> {
    @NotEmpty(message = "UserName field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "UserName can contain latin script and special characters . - _")
    @Size(min = 6, max = 35, message = "User name length must be between 6 and 35 characters.")
    private String userName;

    @NotEmpty(message = "Given name field can not be empty.")
    @Size(min = 1, max = 255, message = "Given name length must be between 1 and 255 characters.")
    private String givenName;

    @NotEmpty(message = "Family name filed can not be empty.")
    @Size(min = 1, max = 255, message = "Family name length must be between 1 and 255 characters.")
    private String familyName;

    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;

    @NotEmpty(message = "Password field can not be empty.")
    @Size(min = 8, max = 35, message = "Password length must be between 8 and 35 characters.")
    private String password;

    @NotEmpty(message = "Confirm password field can not be empty.")
    @Size(min = 8, max = 35, message = "Confirm password length must be between 8 and 35 characters.")
    private String confirmPassword;
    private final Instant createdAt;
    private Set<String> roleIds;
    private Set<String> authorityIds;
    public UserRegisterManualRequest() {
        this.createdAt = Instant.now();
    }
}
