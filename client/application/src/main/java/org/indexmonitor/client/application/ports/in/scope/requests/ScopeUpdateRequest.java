package org.indexmonitor.client.application.ports.in.scope.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScopeUpdateRequest extends SelfValidator<ScopeUpdateRequest> {

    @NotBlank(message = "Client record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;

    @NotBlank(message = "Scope name can not be empty.")
    private String name;

    private String description;

    @NotNull(message = "isEnable property must be defined.")
    private Boolean isEnable;

    @NotNull(message = "isObtainable property must be defined.")
    private Boolean isObtainable;
}
