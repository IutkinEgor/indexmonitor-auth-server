package org.indexmonitor.user.application.ports.in.user.requests;


import org.indexmonitor.common.application.annotations.AppFieldEquality;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@AppFieldEquality(field = "confirmPassword", equalsTo = "password", message = "The password and confirm password do not match.")
public class UserRegisterRequest extends SelfValidator<UserRegisterRequest> {

    @NotEmpty(message = "UserName field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "UserName can contain latin script and special characters . - _")
    @Size(min = 6, max = 35, message = "Length must be between 6 and 35 characters.")
    private String userName;

    @NotEmpty(message = "First name field can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 1 and 255 characters.")
    private String firstName;

    @NotEmpty(message = "Second name filed can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 1 and 255 characters.")
    private String secondName;

    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;

    @NotEmpty(message = "Password field can not be empty.")
    @Size(min = 8, max = 35, message = "Length must be between 8 and 35 characters.")
    private String password;

    @NotEmpty(message = "Confirm password field can not be empty.")
    @Size(min = 8, max = 35, message = "Length must be between 8 and 35 characters.")
    private String confirmPassword;

    @NotEmpty(message = "Question field can not be empty.")
    @Size(min = 8, max = 255, message = "Length must be between 8 and 255 characters.")
    private String recoveryQuestion;

    @NotEmpty(message = "Answer field can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 8 and 255 characters.")
    private String recoveryAnswer;
    @URL
    private String redirectUrl;
    private final Instant createdAt;

    public UserRegisterRequest(String redirectUrl) {
        this.createdAt = Instant.now();
        this.redirectUrl = redirectUrl;
    }

    public UserRegisterRequest() {
        this.createdAt = Instant.now();
    }
}
