package org.indexmonitor.user.adapter.out.persistence.services.role;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.RolePersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.domain.aggregates.Role;
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
public class RoleLoadPersistenceService implements RoleLoadPort {

    private final RoleRepository repository;
    private final RolePersistenceMapper mapper;

    @Override
    public BasePage<Role> findAll(Integer page, Integer size) {
        if (page == null || page < 0) {
            throw new IllegalArgumentException("Offset can not be less than 0.");
        }
        if (size == null || size < 1) {
            throw new IllegalArgumentException("Limit can not be less than 1.");
        }
        Page<RoleEntity> entities = repository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC, "createdAt")));
        return mapper.entityToModel(entities);
    }

    @Override
    public Set<Role> findAllByName(Set<String> names) {
        List<RoleEntity> entity = repository.findAllByNames(names);
        return entity.stream().map(role -> mapper.entityToModel(role)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Optional<Role> findById(BaseId id) {
        Optional<RoleEntity> entity = repository.findById(RoleEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public boolean isExist(Role role) {
        return repository.existsByIdOrName(RoleEntity.convertId(role.getId()),role.getName());
    }

    @Override
    public boolean isExistById(BaseId id) {
        return repository.existsById(RoleEntity.convertId(id));
    }

    @Override
    public boolean isExistByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean isRoleUsedByUser(String roleId) {
        return repository.isUsedByAnyUser(roleId);
    }
}
