package org.indexmonitor.user.adapter.out.persistence.services.role;

import org.indexmonitor.user.adapter.out.persistence.mappers.RolePersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
import org.indexmonitor.user.application.ports.out.role.RoleUpdatePort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class RoleUpdatePersistenceService implements RoleUpdatePort {

    private final RoleRepository roleRepository;
    private final RolePersistenceMapper rolePersistenceMapper;

    @Override
    public void update(Role role) {
        if(role == null){
            throw new NullPointerException("Role is NULL.");
        }
        roleRepository.save(rolePersistenceMapper.modelToEntity(role));
    }
}
