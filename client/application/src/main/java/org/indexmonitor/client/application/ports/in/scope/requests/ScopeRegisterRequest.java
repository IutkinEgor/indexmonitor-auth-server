package org.indexmonitor.client.application.ports.in.scope.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.time.Instant;

@Getter
@Setter
public class ScopeRegisterRequest extends SelfValidator<ScopeRegisterRequest> {

    @NotBlank(message = "Scope name field can not be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:[_.-][a-zA-Z0-9]+)*$", message = "Scope name can contain latin script, special characters . - _ and numbers.")
    @Size(min = 4, max = 35, message = "Scope name length must be between 6 and 35 characters.")
    private String name;
    private String description;
    private Instant createdAt;
    @UUID(message = "Illegal user id format.")
    private String createdBy;

    @NotNull(message = "isEnable property must be defined.")
    private Boolean isEnable;

    @NotNull(message = "isObtainable property must be defined.")
    private Boolean isObtainable;

    public ScopeRegisterRequest() {
        this.createdAt = Instant.now();
    }

//    public BaseId<java.util.UUID> getCreatedBy() {
//        return new BaseId<java.util.UUID>(java.util.UUID.fromString(createdBy));
//    }
}
