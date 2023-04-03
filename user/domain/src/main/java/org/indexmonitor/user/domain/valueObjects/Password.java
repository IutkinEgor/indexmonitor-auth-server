package org.indexmonitor.user.domain.valueObjects;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.models.ValueObject;

import java.io.Serializable;

public class Password extends ValueObject {

    private final String hash;
    private final EncryptionAlgorithm algorithm;

    public Password(String hash, EncryptionAlgorithm algorithm) {
        this.hash = hash;
        this.algorithm = algorithm;
    }
    public String getHash() {
        return this.hash;
    }
    public EncryptionAlgorithm getAlgorithm() {
        return this.algorithm;
    }
}
