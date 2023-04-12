package org.indexmonitor.auth.adapter.out.persistence.mappers.impl;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.adapter.out.persistence.entities.AuthorizationEntity;
import org.indexmonitor.auth.adapter.out.persistence.mappers.AuthorizationPersistenceMapper;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class AuthorizationPersistenceMapperImpl implements AuthorizationPersistenceMapper {

    private RegisteredClientRepository clientRepository;

    @Override
    public AuthorizationEntity modelToEntity(OAuth2Authorization model) {
        AuthorizationEntity entity = AuthorizationEntity.builder()
                .id(model.getId())
                .principalName(model.getPrincipalName())
                .registeredClientId(model.getRegisteredClientId())
                .attributes(model.getAttributes())
                .authorizationGrantType(model.getAuthorizationGrantType())
                .authorizedScopes(model.getAuthorizedScopes())
                .build();
        if(model.getAccessToken() != null){
            entity.addToken(OAuth2AccessToken.class, model.getAccessToken());
        }
        if(model.getRefreshToken() != null){
            entity.addToken(OAuth2RefreshToken.class, model.getRefreshToken());
        }
        return entity;
    }

    @Override
    public OAuth2Authorization entityToModel(AuthorizationEntity model) {
        RegisteredClient client = clientRepository.findByClientId(model.getRegisteredClientId());
        var builder = OAuth2Authorization.withRegisteredClient(client);
        builder.id(model.getId());
        builder.principalName(model.getPrincipalName());
        model.getAttributes().forEach(builder::attribute);
        builder.authorizationGrantType(model.getAuthorizationGrantType());
        builder.authorizedScopes(model.getAuthorizedScopes());
        model.setTokens(model.getTokens());
        return builder.build();
    }
}
