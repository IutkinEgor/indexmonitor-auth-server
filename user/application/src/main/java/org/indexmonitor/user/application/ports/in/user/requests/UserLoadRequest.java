package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoadRequest extends SelfValidator<UserLoadRequest> {
    @NotBlank(message = "User Id can not be empty.")
    private String id;
}
