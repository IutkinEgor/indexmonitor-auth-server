package org.indexmonitor.user.adapter.out.persistence.services.authority;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.mappers.AuthorityPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.AuthorityRepository;
import org.indexmonitor.user.application.ports.out.authority.AuthorityRegisterPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorityRegisterPersistenceService implements AuthorityRegisterPort {

    private final AuthorityRepository repository;
    private final AuthorityPersistenceMapper mapper;
    @Override
    public BaseId generateId() {
        return new BaseId<>(UUID.randomUUID());
    }

    @Override
    public void register(Authority authority) {
        if(authority == null){
            throw new NullPointerException("Authority is NULL.");
        }
        repository.save(mapper.modelToEntity(authority));
    }

}
