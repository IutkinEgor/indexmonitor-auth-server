package org.indexmonitor.client.adapter.out.persistence.mappers.impl;

import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.indexmonitor.client.adapter.out.persistence.mappers.ClientPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.mappers.ClientSettingsPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.mappers.ClientTokenSettingsPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.valueObjects.ClientSecret;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class ClientPersistenceMapperImpl implements ClientPersistenceMapper {

    private ClientSettingsPersistenceMapper clientSettingsPersistenceMapper;
    private ClientTokenSettingsPersistenceMapper clientTokenSettingsPersistenceMapper;
    private ScopePersistenceMapper scopePersistenceMapper;
    @Override
    public ClientEntity modelToEntity(Client model) {
        return ClientEntity.builder()
                .id(model.getId())
                .clientId(model.getClientId())
                .issuedAt(model.getIssuedAt())
                .name(model.getName())
                .description(model.getDescription())
                .secretHash(model.getSecret() != null ? model.getSecret().getSecreteHash() : null)
                .secretAlgorithm(model.getSecret() != null ? model.getSecret().getAlgorithm() : null)
                .authenticationMethods(model.getAuthenticationMethods())
                .authorizationGrantTypes(model.getAuthorizationGrantTypes())
                .origin(model.getOrigin().toString())
                .redirectUris(model.getRedirectUris().stream().map(uri -> uri.toString()).collect(Collectors.toSet()))
                .scopes((model.getScopes() == null || model.getScopes().isEmpty()) ? null : model.getScopes().stream().map(scope -> scopePersistenceMapper.modelToEntity(scope)).collect(Collectors.toSet()))
                .clientSettings(clientSettingsPersistenceMapper.modelToEntity(model.getClientSettings()))
                .tokenSettings(clientTokenSettingsPersistenceMapper.modelToEntity(model.getTokenSettings()))
                .build();
    }

    @Override
    public Client entityToModel(ClientEntity entity) {
        return Client.builder()
                .id(entity.getBaseId())
                .clientId(entity.getClientId())
                .issuedAt(entity.getIssuedAt())
                .name(entity.getName())
                .description(entity.getDescription())
                .secret(entity.getSecretHash() != null && !entity.getSecretHash().isEmpty() ? new ClientSecret(entity.getSecretHash(),entity.getSecretAlgorithm()) : null)
                .authenticationMethods(entity.getAuthenticationMethods())
                .authorizationGrantTypes(entity.getAuthorizationGrantTypes())
                .origin(URI.create(entity.getOrigin()))
                .redirectUris(entity.getRedirectUris().stream().map(uri -> URI.create(uri)).collect(Collectors.toSet()))
                .scopes(entity.getScopes().stream().map(scope -> scopePersistenceMapper.entityToModel(scope)).collect(Collectors.toSet()))
                .clientSettings(clientSettingsPersistenceMapper.entityToModel(entity.getClientSettings()))
                .tokenSettings(clientTokenSettingsPersistenceMapper.entityToModel(entity.getTokenSettings()))
                .build();
    }
}
