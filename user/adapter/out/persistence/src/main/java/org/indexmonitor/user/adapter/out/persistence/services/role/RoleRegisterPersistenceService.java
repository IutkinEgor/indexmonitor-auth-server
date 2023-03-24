package org.indexmonitor.user.adapter.out.persistence.services.role;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.mappers.RolePersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
import org.indexmonitor.user.application.ports.out.role.RoleRegisterPort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleRegisterPersistenceService implements RoleRegisterPort {

    private final RoleRepository repository;
    private final RolePersistenceMapper mapper;
    @Override
    public BaseId generateId() {
        return new BaseId<>(UUID.randomUUID());
    }

    @Override
    public void register(Role role) {
        if(role == null){
            throw new NullPointerException("Role is NULL.");
        }
        repository.save(mapper.modelToEntity(role));
    }
}
