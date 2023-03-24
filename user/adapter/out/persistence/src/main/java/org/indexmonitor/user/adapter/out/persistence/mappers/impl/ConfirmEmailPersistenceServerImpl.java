package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import org.indexmonitor.user.adapter.out.persistence.entities.ConfirmEmailEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ConfirmEmailPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import org.indexmonitor.user.domain.valueObjects.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ConfirmEmailPersistenceServerImpl implements ConfirmEmailPersistenceMapper {

    private final UserPersistenceMapper userMapper;

    @Override
    public ConfirmEmailEntity modelToEntity(ConfirmEmail model) {
        return ConfirmEmailEntity.builder()
                .id(ConfirmEmailEntity.convertId(model.getId()))
                .tokenHash(model.getToken().getTokenHash())
                .tokenAlgorithm(model.getToken().getAlgorithm())
                .issuedAt(model.getToken().getIssuedAt())
                .expireAt(model.getToken().getExpireAt())
                .user(userMapper.modelToEntity(model.getUser()))
                .redirectUrl(model.getRedirectLink())
                .build();
    }

    @Override
    public ConfirmEmail entityToModel(ConfirmEmailEntity entity) {
        Token token = Token.builder()
                .tokenHash(entity.getTokenHash())
                .algorithm(entity.getTokenAlgorithm())
                .issuedAt(entity.getIssuedAt())
                .expireAt(entity.getExpireAt())
                .build();
        return new ConfirmEmail(userMapper.entityToModel(entity.getUser()),token, null, entity.getRedirectUrl());
    }
}
