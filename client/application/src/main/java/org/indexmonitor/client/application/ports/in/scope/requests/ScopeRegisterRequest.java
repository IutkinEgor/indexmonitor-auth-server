package org.indexmonitor.client.application.ports.in.scope.requests;

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

    @NotBlank(message = "Scope name can not be empty.")
    private String name;

    private String description;
    private Instant createdAt;
    //@NotBlank(message = "User id can not be empty.")
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
