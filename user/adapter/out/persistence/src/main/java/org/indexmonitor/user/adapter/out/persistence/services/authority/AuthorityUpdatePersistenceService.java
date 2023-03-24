package org.indexmonitor.user.adapter.out.persistence.services.authority;

import org.indexmonitor.user.adapter.out.persistence.mappers.AuthorityPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.AuthorityRepository;
import org.indexmonitor.user.application.ports.out.authority.AuthorityUpdatePort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthorityUpdatePersistenceService implements AuthorityUpdatePort {

    private final AuthorityRepository authorityRepository;
    private final AuthorityPersistenceMapper authorityPersistenceMapper;

    @Override
    public void update(Authority authority) {
        if(authority == null){
            throw new NullPointerException("Authority is NULL.");
        }
        authorityRepository.save(authorityPersistenceMapper.modelToEntity(authority));
    }
}
