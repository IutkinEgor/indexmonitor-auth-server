package org.indexmonitor.client.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;

public class ClientSettings extends ValueObject {
    private final boolean isRequireProofKey;
    private final boolean isRequireAuthorizationConsent;
    public ClientSettings(boolean isRequireProofKey, boolean isRequireAuthorizationConsent) {
        this.isRequireProofKey = isRequireProofKey;
        this.isRequireAuthorizationConsent = isRequireAuthorizationConsent;
    }
    public static ClientSettings withDefault() {
        return new ClientSettings(false,false);
    }

    public boolean isRequireProofKey() {
        return isRequireProofKey;
    }

    public boolean isRequireAuthorizationConsent() {
        return isRequireAuthorizationConsent;
    }


}
