package org.indexmonitor.user.application.ports.in.user.responses;

import org.indexmonitor.user.domain.aggregates.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {
    private String givenName;
    private String familyName;
    private String email;
    private Boolean emailConfirmed;

    public static UserProfileResponse map(User user){
        return UserProfileResponse.builder()
                .givenName(user.getProfile().getGivenName())
                .familyName(user.getProfile().getGivenName())
                .email(user.getProfile().getEmail() == null ? null : user.getProfile().getEmail())
                .emailConfirmed(user.getProfile().isEmailConfirmed() == null ? false : user.getProfile().isEmailConfirmed())
                .build();
    }
}
