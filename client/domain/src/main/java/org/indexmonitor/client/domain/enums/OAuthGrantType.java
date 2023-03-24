package org.indexmonitor.client.domain.enums;

public enum OAuthGrantType {

    AUTHORIZATION_CODE("authorization_code", "Exchange an authorization code for an access token."),
    CLIENT_CREDENTIALS("client_credentials", "The Client Credentials grant is used when applications request an access token to access their own resources, not on behalf of a user."),
    RESOURCE_OWNER_PASSWORD_CREDENTIALS("password", "Exchange a username and password for an access token."),
    REFRESH_TOKEN("refresh_token", "Obtain a new access token when the current access token has expired via passing refresh token."),
    DEVICE_CODE("device_code", "Obtain an access token for a device without requiring the user to provide credentials");

    private final String grantType;
    private final String description;

    OAuthGrantType(String grantType, String description) {
        this.grantType = grantType;
        this.description = description;
    }

    public String getGrantType() {
        return this.grantType;
    }
    public String getDescription() {
        return this.description;
    }

}

