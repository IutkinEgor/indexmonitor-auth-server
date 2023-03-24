package org.indexmonitor.user.application.ports.in.role.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RoleRegisterRequest extends SelfValidator<RoleRegisterRequest> {
    @NotBlank(message = "Role name field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "Role name can contain latin script, special characters . - _ and numbers.")
    @Size(min = 4, max = 35, message = "Role name length must be between 6 and 35 characters.")
    private String name;
    private String description;
    private Instant createdAt;
    private String createdBy;
    @NotNull(message = "Role isEnable property must be defined.")
    private Boolean isEnable;
    @NotNull(message = "Role isObtainable property must be defined.")
    private Boolean isObtainable;

    public RoleRegisterRequest() {
        this.createdAt = Instant.now();
    }
}
