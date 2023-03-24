package org.indexmonitor.user.domain.models;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.models.BaseEntity;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.valueObjects.Recovery;

public class UserProfile extends BaseEntity<BaseId> {

    private final String givenName;
    private final String familyName;
    private final String email;
    private final Boolean emailConfirmed;
    private final Recovery recovery;

    private UserProfile(Builder builder) {
        super(builder.profileId);
        givenName = builder.givenName;
        familyName = builder.familyName;
        email = builder.email;
        emailConfirmed = builder.emailConfirmed;
        recovery = builder.recovery;
    }

    public String getName() { return givenName + " " + familyName; }
    public String getGivenName() {
        return givenName;
    }
    public String getFamilyName() {
        return familyName;
    }
    public String getEmail() {
        return email;
    }
    public Boolean isEmailConfirmed() {
        return emailConfirmed;
    }
    public Recovery getRecovery() { return recovery; }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private BaseId profileId;
        private String givenName;
        private String familyName;
        private String email;
        private Boolean emailConfirmed = false;
        private Recovery recovery;

        private Builder() {
        }
        public Builder profileId(BaseId id) {
            this.profileId = id;
            return this;
        }
        public Builder givenName(String val) {
            this.givenName = val;
            return this;
        }
        public Builder familyName(String val) {
            this.familyName = val;
            return this;
        }
        public Builder email(String val) {
            this.email = val;
            return this;
        }
        public Builder emailConfirmed(Boolean val) {
            this.emailConfirmed = val;
            return this;
        }
        public Builder recovery(Recovery val) {
            this.recovery = val;
            return this;
        }
        public Builder recovery(String question, String answererHash, EncryptionAlgorithm algorithm) {
            this.recovery = new Recovery(question,answererHash,algorithm);
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
