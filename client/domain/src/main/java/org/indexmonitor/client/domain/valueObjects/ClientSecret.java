package org.indexmonitor.client.domain.valueObjects;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;

public class ClientSecret {
    private final String secreteHash;
    private final EncryptionAlgorithm algorithm;
    public ClientSecret(String secreteHash, EncryptionAlgorithm algorithm) {
        this.secreteHash = secreteHash;
        this.algorithm = algorithm;
    }
    public String getSecreteHash() {
        return secreteHash;
    }
    public EncryptionAlgorithm getAlgorithm() {
        return this.algorithm;
    }
}
