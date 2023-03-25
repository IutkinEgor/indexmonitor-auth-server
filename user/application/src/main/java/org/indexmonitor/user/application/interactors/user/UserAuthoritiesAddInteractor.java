package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityNotFoundException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserAuthoritiesAddUseCase;
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
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class UserAuthoritiesAddInteractor extends Interactor implements UserAuthoritiesAddUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserAuthoritiesAddInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final AuthorityLoadPort authorityLoadPort;

    @Override
    public BaseResponse add(UserAuthoritiesUpdateRequest request) {
        try {
            request.validateSelf();
            Set<Authority> authorities = tryLoadRequestedAuthorities(request);
            User user = tryLoadUser(request);
            tryAddUserAuthorities(user, authorities);
            return onRequestSuccess();
        } catch (Exception e) {
            logger.debug(String.format("Failed to update authorities for user with ID '%s'. Requested authorities IDs: '%s'. Exception message: '%s'.",
                    request.getId() == null ? "null" : request.getId(),
                    request.getAuthorityIds() == null ? "null" : request.getAuthorityIds(),
                    e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private Set<Authority> tryLoadRequestedAuthorities(UserAuthoritiesUpdateRequest request) {
        Set<Authority> authorities = new HashSet<>();
        request.getAuthorityIds().forEach(requestedScope -> {
            Optional<Authority> authority = authorityLoadPort.findById(new BaseId(requestedScope));
            if (authority.isEmpty()) {
                throw new AuthorityNotFoundException();
            }
            authorities.add(authority.get());
        });
        return authorities;
    }

    private User tryLoadUser(UserAuthoritiesUpdateRequest request) {
        Optional<User> client = userLoadPort.findByUserId(new BaseId(request.getId()));
        if (client.isEmpty()) {
            throw new UserNotFoundException();
        }
        return client.get();
    }

    private void tryAddUserAuthorities(User user, Set<Authority> newAuthorities) {
        Set<UserAuthority> authorities = user.getAuthorities();
        authorities.addAll(newAuthorities.stream().map(authority -> new UserAuthority(authority.getId(),authority.getName())).collect(Collectors.toSet()));
        user.updateAuthorities(authorities);
        userUpdatePort.update(user);
    }
}
