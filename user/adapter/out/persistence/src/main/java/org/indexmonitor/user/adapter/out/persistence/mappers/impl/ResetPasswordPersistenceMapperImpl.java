package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import org.indexmonitor.user.adapter.out.persistence.entities.ResetPasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ResetPasswordPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.domain.aggregates.ResetPassword;
import org.indexmonitor.user.domain.valueObjects.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ResetPasswordPersistenceMapperImpl implements ResetPasswordPersistenceMapper {

    private final UserPersistenceMapper userMapper;

    @Override
    public ResetPasswordEntity modelToEntity(ResetPassword model) {
        return  ResetPasswordEntity.builder()
                .id(model.getId())
                .tokenHash(model.getToken().getTokenHash())
                .tokenAlgorithm(model.getToken().getAlgorithm())
                .issuedAt(model.getToken().getIssuedAt())
                .expireAt(model.getToken().getExpireAt())
                .user(userMapper.modelToEntity(model.getUser()))
                .build();
    }

    @Override
    public ResetPassword entityToModel(ResetPasswordEntity entity) {
        Token token = Token.builder()
                .tokenHash(entity.getTokenHash())
                .algorithm(entity.getTokenAlgorithm())
                .issuedAt(entity.getIssuedAt())
                .expireAt(entity.getExpireAt())
                .build();
        return new ResetPassword(entity.getId(),token,userMapper.entityToModel(entity.getUser()));
    }
}
