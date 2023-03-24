package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleNotFoundException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserRolesAddUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRolesUpdateRequest;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.Role;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class UserRolesAddInteractor  extends Interactor implements UserRolesAddUseCase {

    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final RoleLoadPort roleLoadPort;

    @Override
    public BaseResponse add(UserRolesUpdateRequest request) {
        try {
            request.validateSelf();
            Set<Role> roles = tryLoadRequestedRoles(request);
            User user = tryLoadUser(request);
            tryAddUserRoles(user, roles);
            return onRequestSuccess();
        } catch (Exception e) {
            return onRequestFailure(e);
        }
    }

    private Set<Role> tryLoadRequestedRoles(UserRolesUpdateRequest request) {
        Set<Role> scopes = new HashSet<>();
        request.getRoleIds().forEach(requestedScope -> {
            Optional<Role> scope = roleLoadPort.findById(new BaseId(requestedScope));
            if (scope.isEmpty()) {
                throw new RoleNotFoundException();
            }
            scopes.add(scope.get());
        });
        return scopes;
    }

    private User tryLoadUser(UserRolesUpdateRequest request) {
        Optional<User> client = userLoadPort.findByUserId(new BaseId(request.getId()));
        if (client.isEmpty()) {
            throw new UserNotFoundException();
        }
        return client.get();
    }

    private void tryAddUserRoles(User user, Set<Role> newRoles) {
        Set<UserRole> roles = user.getRoles();
        roles.addAll(newRoles.stream().map(role -> new UserRole(role.getId(),role.getName())).collect(Collectors.toSet()));
        user.updateRoles(roles);
        userUpdatePort.update(user);
    }
}
