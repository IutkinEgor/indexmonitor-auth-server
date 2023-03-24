package org.indexmonitor.user.application.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.mappers.UserMapper;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterManualRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.models.UserProfile;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.Recovery;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class UserMapperImpl implements UserMapper {

    @Override
    public User mapRegisterRequest(UserRegisterRequest request, Password password, Recovery recovery, Boolean isEnabled) {

        UUID id = UUID.randomUUID();

        UserProfile userProfile = UserProfile.builder()
                .profileId(BaseId.map(id))
                .givenName(request.getFirstName())
                .familyName(request.getSecondName())
                .email(request.getEmail())
                .emailConfirmed(false)
                .recovery(recovery)
                .build();

        User user = User.builder()
                .userId(BaseId.map(id))
                .userName(request.getUserName())
                .profile(userProfile)
                .password(password)
                .createdAt(request.getCreatedAt())
                .isUserNonLocked(true)
                .isUserNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(isEnabled)
                .build();

        return user;
    }

    @Override
    public UserRegisterRequest mapRegisterRequest(User user) {
        return UserRegisterRequest.builder()
                // .userName(user.getUserName().getValue())
                .recoveryQuestion(user.getProfile().getRecovery().getQuestion())
                //.firstName(user.getProfile().getFirstName().getValue())
                // .secondName(user.getProfile().getSecondName().getValue())
                // .email(user.getProfile().getEmail().getValue())
                .build();
    }

    @Override
    public User mapRegisterManualRequest(UserRegisterManualRequest request, BaseId userId, Password password, Recovery recovery) {
        UserProfile userProfile = UserProfile.builder()
                .profileId(userId)
                .givenName(request.getGivenName())
                .familyName(request.getFamilyName())
                .email(request.getEmail())
                .emailConfirmed(true)
                .recovery(recovery)
                .build();

        User user = User.builder()
                .userId(userId)
                .userName(request.getUserName())
                .profile(userProfile)
                .password(password)
                .createdAt(request.getCreatedAt())
                .isUserNonLocked(true)
                .isUserNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        return user;
    }



}
