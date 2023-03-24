package org.indexmonitor.user.adapter.out.persistence.services.user;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.UserRepository;
import org.indexmonitor.user.application.ports.out.user.UserRegisterPort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserRegisterPersistenceService implements UserRegisterPort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    @Override
    public BaseId getNewId() {
        return BaseId.map(UUID.randomUUID());
    }
    @Override
    public BaseId register(User user) {
        UserEntity entity = userRepository.save(userPersistenceMapper.modelToEntity(user));
        return BaseId.map(entity.getUserId());
    }
}
