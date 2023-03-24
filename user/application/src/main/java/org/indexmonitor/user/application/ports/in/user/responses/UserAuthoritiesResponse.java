package org.indexmonitor.user.application.ports.in.user.responses;

import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserAuthoritiesResponse {
    private String authorityId;
    private String authorityName;

    public static Set<UserAuthoritiesResponse> map(User user){
        if(user.getAuthorities() == null || user.getAuthorities().isEmpty()){
            return new HashSet<>();
        }
        return user.getAuthorities().stream().map(authority ->
                        new UserAuthoritiesResponse(authority.getAuthorityId().getValueAsString(),authority.getAuthorityName())
                ).collect(Collectors.toSet());
    }

}
