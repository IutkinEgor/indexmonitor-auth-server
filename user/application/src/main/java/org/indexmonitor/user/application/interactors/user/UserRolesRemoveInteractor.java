package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleNotFoundException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserRolesRemoveUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRolesUpdateRequest;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.Role;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.UserRole;
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
class UserRolesRemoveInteractor extends Interactor implements UserRolesRemoveUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserRolesRemoveInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final RoleLoadPort roleLoadPort;

    @Override
    public BaseResponse remove(UserRolesUpdateRequest request) {
        try{
            request.validateSelf();
            Set<Role> roles = tryLoadRequestedRoles(request);
            User user = tryLoadUser(request);
            tryRemoveUserRole(user, roles);
            return  onRequestSuccess();
        }
        catch (Exception e){
            logger.debug(String.format("Failed to remove roles for user with ID '%s'. Roles IDs: '%s'. Exception message: '%s'.",
                    request.getId() == null ? "null" : request.getId(),
                    request.getRoleIds() == null ? "null" : request.getRoleIds(),
                    e.getMessage()));
            return onRequestFailure(e);
        }
    }
    private Set<Role> tryLoadRequestedRoles(UserRolesUpdateRequest request){
        Set<Role> roles= new HashSet<>();
        request.getRoleIds().forEach(requestedRole -> {
            Optional<Role> scope = roleLoadPort.findById(new BaseId(requestedRole));
            if(scope.isEmpty()){
                throw new RoleNotFoundException();
            }
            roles.add(scope.get());
        });
        return roles;
    }

    private User tryLoadUser(UserRolesUpdateRequest request){
        Optional<User> user = userLoadPort.findByUserId(new BaseId(request.getId()));
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    private void tryRemoveUserRole(User user, Set<Role> newRoles){
        Set<UserRole> roles = user.getRoles();
        roles.removeAll(newRoles.stream().map(role -> new UserRole(role.getId(),role.getName())).collect(Collectors.toSet()));
        user.updateRoles(roles);
        userUpdatePort.update(user);
    }
}
