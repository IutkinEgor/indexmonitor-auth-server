package org.indexmonitor.user.adapter.out.persistence.services.user;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.AuthorityRepository;
import org.indexmonitor.user.adapter.out.persistence.repositories.UserRepository;
import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.UserAuthority;
import org.indexmonitor.user.domain.valueObjects.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserLoadPersistenceService implements UserLoadPort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public BasePage<User> findAll(Integer offset, Integer limit) {
        if (offset == null || offset < 0) {
            throw new IllegalArgumentException("Offset can not be less than 0.");
        }
        if (limit == null || limit < 1) {
            throw new IllegalArgumentException("Limit can not be less than 1.");
        }
        Page<UserEntity> entities = userRepository.findAll(PageRequest.of(offset,limit, Sort.by(Sort.Direction.ASC,"createdAt")));
        entities.forEach(entity -> { entity.setDomainRoles(loadUserRoles(entity.getRoles())); entity.setDomainAuthorities(loadUserAuthorities(entity.getAuthorities()));});
        return userPersistenceMapper.entityToModel(entities);
//        return entity.stream().map(user -> {
//                    user.setDomainRoles(loadUserRoles(user.getRoles()));
//                    user.setDomainAuthorities(loadUserAuthorities(user.getAuthorities()));
//                    return userPersistenceMapper.entityToModel(user);
//                }
//        ).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Optional<User> findByUserId(BaseId id) {
        if(id == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<UserEntity> entity = userRepository.findById(UserEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        entity.get().setDomainRoles(loadUserRoles(entity.get().getRoles()));
        entity.get().setDomainAuthorities(loadUserAuthorities(entity.get().getAuthorities()));
        return Optional.of(userPersistenceMapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        if(userName == null || userName.isEmpty()){
            throw new NullPointerException("User name is empty.");
        }
        Optional<UserEntity> entity = userRepository.findByUserName(userName);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        entity.get().setDomainRoles(loadUserRoles(entity.get().getRoles()));
        entity.get().setDomainAuthorities(loadUserAuthorities(entity.get().getAuthorities()));
        return Optional.of(userPersistenceMapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new NullPointerException("User email is empty.");
        }
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        entity.get().setDomainRoles(loadUserRoles(entity.get().getRoles()));
        entity.get().setDomainAuthorities(loadUserAuthorities(entity.get().getAuthorities()));
        return Optional.of(userPersistenceMapper.entityToModel(entity.get()));
    }

    @Override
    public boolean isExist(User user) {
        return userRepository.existsByIdOrUsernameOrEmail(UserEntity.convertId(user.getId()),user.getUserName(),user.getProfile().getEmail());
    }

    @Override
    public boolean isExistMoreThanOne(User user) {
        return userRepository.existsByIdOrUsernameOrEmailMoreThanOne(UserEntity.convertId(user.getId()),user.getUserName(),user.getProfile().getEmail());
    }

    @Override
    public boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isExistByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    private Set<UserRole> loadUserRoles(Set<String> userRoles){
        return roleRepository.findAllById(userRoles
                .stream().map(UUID::fromString).collect(Collectors.toSet()))
                .stream().map(role -> new UserRole(BaseId.map(role.getId()),role.getName())).collect(Collectors.toSet());
    }
    private Set<UserAuthority> loadUserAuthorities(Set<String> userAuthorities){
        return authorityRepository.findAllById(userAuthorities
                        .stream().map(UUID::fromString).collect(Collectors.toSet()))
                .stream().map(role -> new UserAuthority(BaseId.map(role.getId()),role.getName())).collect(Collectors.toSet());
    }
}
