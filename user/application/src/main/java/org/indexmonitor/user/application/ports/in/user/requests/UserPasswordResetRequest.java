package org.indexmonitor.user.application.ports.in.user.requests;


import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordResetRequest extends SelfValidator<UserPasswordResetRequest> {

    @NotEmpty(message = "Email field can not be empty.")
    @Email(message = "Invalid email pattern.")
    private String email;

    @NotEmpty(message = "Question field can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 8 and 255 characters.")
    private String recoveryQuestion;

    @NotEmpty(message = "Answer field can not be empty.")
    @Size(min = 1, max = 255, message = "Length must be between 8 and 255 characters.")
    private String recoveryQuestionAnswer;

    private String redirectLink;
}
