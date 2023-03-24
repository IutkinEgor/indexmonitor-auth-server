package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.entities.UserProfileEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.models.UserProfile;
import org.indexmonitor.user.domain.valueObjects.Password;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class UserPersistenceMapperImpl implements UserPersistenceMapper {

    @Override
    public UserEntity modelToEntity(User model) {

        UserProfileEntity userProfile = UserProfileEntity.builder()
                .profileId(UserProfileEntity.convertId(model.getId()))
                .givenName(model.getProfile().getGivenName())
                .familyName(model.getProfile().getFamilyName())
                .email(model.getProfile().getEmail())
                .email_confirmed(model.getProfile().isEmailConfirmed())
                .recoveryQuestion(model.getProfile().getRecovery().getQuestion())
                .recoveryAnswerHash(model.getProfile().getRecovery().getAnswer())
                .recoveryAnswerAlgorithm(model.getProfile().getRecovery().getAlgorithm())
                .build();

        return UserEntity.builder()
                .userId(UserEntity.convertId(model.getId()))
                .userName(model.getUserName())
                .password(model.getPassword().getHash())
                .algorithm(model.getPassword().getAlgorithm())
                .roles(model.getRoles() == null ? null : model.getRoles().stream().map(role -> role.getRoleId().getValueAsString()).collect(Collectors.toSet()))
                .authorities(model.getAuthorities() == null ? null : model.getAuthorities().stream().map(authority -> authority.getAuthorityId().getValueAsString()).collect(Collectors.toSet()))
                .profile(userProfile)
                .createdAt(model.getCreatedAt())
                .isAccountNonExpired(model.isUserNonExpired())
                .isCredentialsNonExpired(model.isCredentialsNonExpired())
                .isAccountNonLocked(model.isUserNonLocked())
                .isEnabled(model.isEnabled())
                .build();
    }

    @Override
    public User entityToModel(UserEntity entity) {

        UserProfile userProfile = UserProfile.builder()
                .profileId(BaseId.map(entity.getProfile().getProfileId()))
                .givenName(entity.getProfile().getGivenName())
                .familyName(entity.getProfile().getFamilyName())
                .email(entity.getProfile().getEmail())
                .emailConfirmed(entity.getProfile().getEmail_confirmed())
                .recovery(entity.getProfile().getRecoveryQuestion(),entity.getProfile().getRecoveryAnswerHash(), entity.getProfile().getRecoveryAnswerAlgorithm())
                .build();

        return User.builder()
                .userId(BaseId.map(entity.getUserId()))
                .userName(entity.getUserName())
                .password(new Password(entity.getPassword(),entity.getAlgorithm()))
                .roles(entity.getRoles() == null ? null : entity.getDomainRoles())
                .authorities(entity.getDomainAuthorities() == null ? null : entity.getDomainAuthorities())
                .profile(userProfile)
                .createdAt(entity.getCreatedAt())
                .isUserNonExpired(entity.getIsAccountNonExpired())
                .isCredentialsNonExpired(entity.getIsCredentialsNonExpired())
                .isUserNonLocked(entity.getIsAccountNonLocked())
                .isEnabled(entity.getIsEnabled())
                .build();
    }

    @Override
    public BasePage<User> entityToModel(Page<UserEntity> entities) {
        return BasePage.<User>builder()
                .elements(entities.getContent().stream().map(entity -> entityToModel(entity)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(entities.getTotalElements())
                .currentPage(entities.getPageable().getPageNumber())
                .currentSize(entities.getPageable().getPageSize())
                .build();
    }
}
