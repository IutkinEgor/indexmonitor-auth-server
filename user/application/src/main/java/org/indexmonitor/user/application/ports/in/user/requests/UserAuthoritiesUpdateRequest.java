package org.indexmonitor.user.application.ports.in.user.requests;

import org.indexmonitor.common.application.exception.AppValidationException;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthoritiesUpdateRequest extends SelfValidator<UserAuthoritiesUpdateRequest> {
    @NotBlank(message = "User Id can not be empty.")
    private String id;
    private Set<String> authorityIds;


    public UserAuthoritiesUpdateRequest(String id, Set<String> authorityIds) {
        this.id = id;
        this.authorityIds = authorityIds;
    }
    public UserAuthoritiesUpdateRequest(String id, String... authorityIds) {
        this.id = id;
        this.authorityIds = Arrays.stream(authorityIds).collect(Collectors.toSet());
    }


    @Override
    public void validateSelf() {
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<UserAuthoritiesUpdateRequest>> constraintViolations = super.getObjectConstraints(this);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        authoritiesValidation(errors);
        if (!errors.isEmpty()) {
            throw new AppValidationException(errors);
        }
    }

    private Set<String> authoritiesValidation(Set<String> errors){
        if(authorityIds == null && authorityIds.isEmpty()) return errors;
        authorityIds.forEach(scope -> {
            if(scope.isEmpty()) {
                authorityIds.remove(scope);
            }
        });
        return errors;
    }
}
