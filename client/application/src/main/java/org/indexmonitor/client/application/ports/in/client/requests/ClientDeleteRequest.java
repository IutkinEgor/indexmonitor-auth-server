package org.indexmonitor.client.application.ports.in.client.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;


@Getter
@Setter
@AllArgsConstructor
public class ClientDeleteRequest extends SelfValidator<ClientDeleteRequest> {

    @NotBlank(message = "Client Record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;

    public java.util.UUID getId() {
        return java.util.UUID.fromString(id);
    }

}
