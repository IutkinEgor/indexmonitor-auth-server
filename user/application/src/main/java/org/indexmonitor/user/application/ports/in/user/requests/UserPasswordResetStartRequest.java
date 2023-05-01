package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserPasswordResetStartRequest extends SelfValidator<UserPasswordResetStartRequest> {
    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
