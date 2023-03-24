package org.indexmonitor.user.application.ports.in.authority.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AuthoritySettingsLoadRequest extends SelfValidator<AuthoritySettingsLoadRequest> {
    @NotBlank(message = "Authority Id can not be empty.")
    private String id;
}
