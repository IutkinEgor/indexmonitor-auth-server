package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityNotFoundException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserAuthoritiesRemoveUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserAuthoritiesUpdateRequest;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.Authority;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.UserAuthority;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class UserAuthoritiesRemoveInteractor extends Interactor implements UserAuthoritiesRemoveUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserAuthoritiesRemoveInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final AuthorityLoadPort authorityLoadPort;

    @Override
    public BaseResponse remove(UserAuthoritiesUpdateRequest request) {
        try{
            request.validateSelf();
            Set<Authority> authorities = tryLoadRequestedAuthorities(request);
            User user = tryLoadUser(request);
            tryRemoveUserAuthority(user, authorities);
            return  onRequestSuccess();
        }
        catch (Exception e){
            logger.debug(String.format("Failed to remove authorities for user with ID '%s'. Authorities IDs: '%s'. Exception message: '%s'.",
                    request.getId() == null ? "null" : request.getId(),
                    request.getAuthorityIds() == null ? "null" : request.getAuthorityIds(),
                    e.getMessage()));
            return onRequestFailure(e);
        }
    }
    private Set<Authority> tryLoadRequestedAuthorities(UserAuthoritiesUpdateRequest request){
        Set<Authority> authorities= new HashSet<>();
        request.getAuthorityIds().forEach(requestedRole -> {
            Optional<Authority> authority = authorityLoadPort.findById(new BaseId(requestedRole));
            if(authority.isEmpty()){
                throw new AuthorityNotFoundException();
            }
            authorities.add(authority.get());
        });
        return authorities;
    }

    private User tryLoadUser(UserAuthoritiesUpdateRequest request){
        Optional<User> user = userLoadPort.findByUserId(new BaseId(request.getId()));
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    private void tryRemoveUserAuthority(User user, Set<Authority> newAuthorities){
        Set<UserAuthority> authorities = user.getAuthorities();
        authorities.removeAll(newAuthorities.stream().map(authority -> new UserAuthority(authority.getId(),authority.getName())).collect(Collectors.toSet()));
        user.updateAuthorities(authorities);
        userUpdatePort.update(user);
    }
}
