package org.indexmonitor.client.adapter.out.persistence.services.client;

import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.mappers.ClientPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ClientRepository;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class ClientLoadPersistService implements ClientLoadPort {

    private final ClientRepository repository;
    private final ClientPersistenceMapper mapper;

    public ClientLoadPersistService(ClientRepository repository, ClientPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BasePage<Client> findAll(Integer page, Integer size) {
        Page<ClientEntity> entities = repository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC, "issuedAt")));
        return mapper.entityToModel(entities);
    }
    @Override
    public Set<Client> findAllByScopeId(BaseId scopeId) {
        if (scopeId == null) {
            throw new IllegalArgumentException("Scope id is NULL.");
        }
        Set<ClientEntity> entity = repository.findAllByScopeId(ScopeEntity.convertId(scopeId));
        return entity.stream().map(client -> mapper.entityToModel(client)).collect(Collectors.toCollection(LinkedHashSet::new));
    }
    @Override
    public Optional<Client> findById(BaseId id) {
        if(id == null){
            throw new NullPointerException("Client record Id is NULL");
        }
        Optional<ClientEntity> entity = repository.findById(ClientEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<Client> findByClientId(String id) {
        if(id == null || id.isEmpty()) {
            throw new NullPointerException("Client Id is empty.");
        }
        Optional<ClientEntity> entity = repository.findByClientId(id);
        if(entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<Client> findByName(String name) {
        if(name == null || name.isEmpty()) {
            throw new NullPointerException("Client name is empty.");
        }
        Optional<ClientEntity> entity = repository.findByClientName(name);
        if(entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<Client> findByOrigin(String origin) {
        if(origin == null || origin.isEmpty()) {
            throw new NullPointerException("Client origin is empty.");
        }
        Optional<ClientEntity> entity = repository.findByOrigin(origin);
        if(entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<Set<Scope>> findScopes(BaseId id) {
        if(id == null) {
            throw new NullPointerException("Client Id is empty.");
        }
        Optional<ClientEntity> entity = repository.findById(ClientEntity.convertId(id));
        if(entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()).getScopes());
    }

    @Override
    public Boolean isExist(Client client) {
        return repository.existsByIdOrClientIdOrClientNameOrOrigin(
                ClientEntity.convertId(client.getId()),
                client.getClientId(),
                client.getName(),
                client.getOrigin().toString());
    }

    @Override
    public Boolean isExistMoreThanOne(Client client) {
        return repository.existsMoreThenOneByIdOrClientIdOrClientNameOrOrigin(
                ClientEntity.convertId(client.getId()),
                client.getClientId(),
                client.getName(),
                client.getOrigin().toString());
    }

    public Boolean isExistById(BaseId id){
        if(id == null){
            throw new NullPointerException("Client record Id is NULL.");
        }
        return repository.existsById(ClientEntity.convertId(id));
    }

}
