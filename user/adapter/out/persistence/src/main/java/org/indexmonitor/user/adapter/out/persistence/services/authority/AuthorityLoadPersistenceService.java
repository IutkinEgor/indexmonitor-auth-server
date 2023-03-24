package org.indexmonitor.user.adapter.out.persistence.services.authority;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.AuthorityEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.AuthorityPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.AuthorityRepository;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AuthorityLoadPersistenceService implements AuthorityLoadPort {

    private final AuthorityRepository repository;
    private final AuthorityPersistenceMapper mapper;

    @Override
    public BasePage<Authority> findAll(Integer page, Integer size) {
        if (page == null || page < 0) {
            throw new IllegalArgumentException("Offset can not be less than 0.");
        }
        if (size == null || size < 1) {
            throw new IllegalArgumentException("Limit can not be less than 1.");
        }
        Page<AuthorityEntity> entities = repository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC, "createdAt")));
        return mapper.entityToModel(entities);
    }

    @Override
    public Set<Authority> findAllByName(Set<String> names) {
        List<AuthorityEntity> entity = repository.findAllByNames(names);
        return entity.stream().map(role -> mapper.entityToModel(role)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Optional<Authority> findById(BaseId id) {
        Optional<AuthorityEntity> entity = repository.findById(AuthorityEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public boolean isExist(Authority authority) {
        return repository.existsByIdOrName(AuthorityEntity.convertId(authority.getId()),authority.getName());
    }

    @Override
    public boolean isExistById(BaseId id) {
        return repository.existsById(AuthorityEntity.convertId(id));
    }

    @Override
    public boolean isExistByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean isAuthorityUsedByUser(String authorityId) {
        return repository.isUsedByAnyUser(authorityId);
    }
}
