package org.indexmonitor.common.domain.enums;

public enum AuthenticationMethod {

    CLIENT_SECRET_BASIC("client_secret_basic", ""),
    CLIENT_SECRET_POST("client_secret_post", ""),
    CLIENT_SECRET_JWT("client_secret_jwt", ""),
    PRIVATE_KEY_JWT("private_key_jwt", ""),
    NONE("none", "");
    private final String method;
    private final String description;

    AuthenticationMethod(String method, String description) {
        this.method = method;
        this.description = description;
    }

    public String getMethod() {
        return this.method;
    }
    public String getDescription() {
        return this.description;
    }
}

