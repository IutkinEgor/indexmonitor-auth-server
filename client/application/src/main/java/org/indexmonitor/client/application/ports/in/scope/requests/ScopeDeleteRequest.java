package org.indexmonitor.client.application.ports.in.scope.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScopeDeleteRequest extends SelfValidator<ScopeDeleteRequest> {
    @NotBlank(message = "Client record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;
    public java.util.UUID getId() {
        return java.util.UUID.fromString(id);
    }
}
