package org.indexmonitor.user.adapter.out.persistence.services.user;

import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.UserRepository;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserUpdatePersistenceService implements UserUpdatePort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public void update(User user) {
        userRepository.save(userPersistenceMapper.modelToEntity(user));
    }
}
