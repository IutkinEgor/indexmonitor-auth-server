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
public class UserRolesUpdateRequest extends SelfValidator<UserRolesUpdateRequest> {

    @NotBlank(message = "User Id can not be empty.")
    private String id;
    private Set<String> roleIds;


    public UserRolesUpdateRequest(String id, Set<String> roleIds) {
        this.id = id;
        this.roleIds = roleIds;
    }
    public UserRolesUpdateRequest(String id, String... roleIds) {
        this.id = id;
        this.roleIds = Arrays.stream(roleIds).collect(Collectors.toSet());
    }


    @Override
    public void validateSelf() {
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<UserRolesUpdateRequest>> constraintViolations = super.getObjectConstraints(this);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        rolesValidation(errors);
        if (!errors.isEmpty()) {
            throw new AppValidationException(errors);
        }
    }

    private Set<String> rolesValidation(Set<String> errors){
        if(roleIds == null && roleIds.isEmpty()) return errors;
        roleIds.forEach(scope -> {
            if(scope.isEmpty()) {
                roleIds.remove(scope);
            }
        });
        return errors;
    }
}