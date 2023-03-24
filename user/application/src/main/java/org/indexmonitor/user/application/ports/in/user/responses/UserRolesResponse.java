package org.indexmonitor.user.application.ports.in.user.responses;

import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserRolesResponse {
    private String roleId;
    private String roleName;

    public static Set<UserRolesResponse> map(User user){
        if(user.getRoles() == null || user.getRoles().isEmpty()){
            return new HashSet<>();
        }
        return user.getRoles().stream().map(role ->
                new UserRolesResponse(role.getRoleId().getValueAsString(),role.getRoleName())
        ).collect(Collectors.toSet());
    }
}
