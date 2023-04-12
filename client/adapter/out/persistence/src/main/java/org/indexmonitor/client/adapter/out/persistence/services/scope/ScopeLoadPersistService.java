package org.indexmonitor.client.adapter.out.persistence.services.scope;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ScopeRepository;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import lombok.AllArgsConstructor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ScopeLoadPersistService implements ScopeLoadPort {

    private final ScopeRepository repository;
    private final ScopePersistenceMapper mapper;

    @Override
    public BasePage<Scope> findAll(Integer page, Integer size) {
        Page<ScopeEntity> entities = repository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC, "createdAt")));
        return mapper.entityToModel(entities);
    }

    @Override
    public Set<Scope> findAllById(Set<BaseId> baseIdList) {
        Set<ScopeEntity> entities = repository.findAllById(baseIdList.stream().map(id -> ScopeEntity.convertId(id)).collect(Collectors.toSet()));
        return entities.stream().map(entity -> mapper.entityToModel(entity)).collect(Collectors.toSet());
    }

    @Override
    public Optional<Scope> findById(BaseId id) {
        if(id == null){
            throw new NullPointerException("Client record Id is NULL.");
        }
        Optional<ScopeEntity> entity = repository.findById(ScopeEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<Scope> findByName(String name) {
        if(name == null || name.isEmpty()) {
            throw new NullPointerException("Scope name is empty.");
        }
        Optional<ScopeEntity> entity = repository.findByName(name);
        if(entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Boolean isExist(Scope scope) {
        return repository.existsByIdOrName(ScopeEntity.convertId(scope.getId()), scope.getName());
    }

    @Override
    public Boolean isExistMoreThanOne(Scope scope) {
        return repository.existsMoreThenOneByIdOrName(ScopeEntity.convertId(scope.getId()), scope.getName());
    }

    public Boolean isExistById(BaseId id){
        if(id == null){
            throw new NullPointerException("Scope record Id is NULL.");
        }
        return repository.existsById(ScopeEntity.convertId(id));
    }

}
