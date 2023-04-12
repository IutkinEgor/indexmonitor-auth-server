package org.indexmonitor.user.application.ports.in.user.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.User;

import java.io.Serializable;

@Getter
@Setter
public class UserPasswordResetResponse implements Serializable {
    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;
    @NotEmpty(message = "Question field can not be empty.")
    @Size(min = 8, max = 255, message = "Length must be between 8 and 255 characters.")
    private String recoveryQuestion;

    public static UserPasswordResetResponse map(User user) {
        UserPasswordResetResponse response = new UserPasswordResetResponse();
        response.email = user.getProfile().getEmail();
        response.recoveryQuestion = user.getProfile().getRecovery().getQuestion();
        return response;
    }
}
