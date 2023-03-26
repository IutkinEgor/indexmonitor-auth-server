package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.annotations.AppFieldEquality;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AppFieldEquality(field = "confirmPassword", equalsTo = "password", message = "The password and confirm password do not match.")
public class UserPasswordResetCallbackRequest extends SelfValidator<UserPasswordResetCallbackRequest> {

    @NotEmpty(message = "User Id can not be empty.")
    private String userId;

    @NotEmpty(message = "Password field can not be empty.")
    @Size(min = 8, max = 35, message = "Length must be between 8 and 35 characters.")
    private String password;

    @NotEmpty(message = "Confirm password field can not be empty.")
    @Size(min = 8, max = 35, message = "Length must be between 8 and 35 characters.")
    private String confirmPassword;

}
