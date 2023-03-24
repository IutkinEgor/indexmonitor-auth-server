package org.indexmonitor.client.application.ports.in.client.requests;

import org.indexmonitor.common.application.exception.AppValidationException;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class ClientUpdateScopeRequest extends SelfValidator<ClientUpdateScopeRequest> {

    @NotBlank(message = "Client record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;
    private Set<String> scopeIds;


    public ClientUpdateScopeRequest(String id, Set<String> scopeIds) {
        this.id = id;
        this.scopeIds = scopeIds;
    }
    public ClientUpdateScopeRequest(String id, String... scopeIds) {
        this.id = id;
        this.scopeIds = Arrays.stream(scopeIds).collect(Collectors.toSet());
    }


    @Override
    public void validateSelf() {
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<ClientUpdateScopeRequest>> constraintViolations = super.getObjectConstraints(this);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        scopesValidation(errors);
        if (!errors.isEmpty()) {
            throw new AppValidationException(errors);
        }
    }

    private Set<String> scopesValidation(Set<String> errors){
        if(scopeIds == null && scopeIds.isEmpty()) return errors;
        scopeIds.forEach(scope -> {
            if(scope.isEmpty()) {
                scopeIds.remove(scope);
                return;
            }
        });
        return errors;
    }
}
