package org.indexmonitor.user.application.ports.in.user.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.indexmonitor.common.application.models.SelfValidator;

@Getter
@Setter
@AllArgsConstructor
public class UserPasswordChangeRequest extends SelfValidator<UserPasswordChangeRequest> {
    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;
    @NotEmpty(message = "Password field can not be empty.")
    @Size(min = 8, max = 35, message = "Password length must be between 8 and 35 characters.")
    private String password;
    private String redirectLink;

}
