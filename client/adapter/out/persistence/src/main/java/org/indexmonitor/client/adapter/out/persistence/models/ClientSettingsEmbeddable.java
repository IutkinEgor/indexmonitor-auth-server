package org.indexmonitor.client.adapter.out.persistence.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSettingsEmbeddable {
    private boolean isRequireProofKey;
    private boolean isRequireAuthorizationConsent;
}
