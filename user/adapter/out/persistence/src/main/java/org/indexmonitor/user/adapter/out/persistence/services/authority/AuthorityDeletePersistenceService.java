package org.indexmonitor.user.adapter.out.persistence.services.authority;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.AuthorityEntity;
import org.indexmonitor.user.adapter.out.persistence.repositories.AuthorityRepository;
import org.indexmonitor.user.application.ports.out.authority.AuthorityDeletePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthorityDeletePersistenceService implements AuthorityDeletePort {

    private final AuthorityRepository repository;

    @Override
    public void delete(BaseId id) {
        repository.deleteById(AuthorityEntity.convertId(id));
    }
}
