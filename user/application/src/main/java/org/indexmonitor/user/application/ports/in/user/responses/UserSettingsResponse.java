package org.indexmonitor.user.application.ports.in.user.responses;

import org.indexmonitor.user.domain.aggregates.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSettingsResponse {
    private String userName;
    private Boolean isUserNonExpired;
    private Boolean isUserNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    public static UserSettingsResponse map(User user){
        return UserSettingsResponse.builder()
                .userName(user.getUserName())
                .isUserNonExpired(user.isUserNonExpired())
                .isUserNonLocked(user.isUserNonLocked())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .isEnabled(user.isEnabled())
                .build();
    }
}
