package org.indexmonitor.user.domain.valueObjects;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;

public class Recovery {
    private final String question;
    private final String answer;
    private final EncryptionAlgorithm algorithm;
    public Recovery(String question, String answer, EncryptionAlgorithm algorithm) {
        this.question = question;
        this.answer = answer;
        this.algorithm = algorithm;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }

    public EncryptionAlgorithm getAlgorithm() {
        return algorithm;
    }
}
