package org.indexmonitor.user.adapter.out.persistence.services.role;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
import org.indexmonitor.user.application.ports.out.role.RoleDeletePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class RoleDeletePersistenceService implements RoleDeletePort {

    private final RoleRepository repository;

    @Override
    public void delete(BaseId id) {
        repository.deleteById(RoleEntity.convertId(id));
    }
}
