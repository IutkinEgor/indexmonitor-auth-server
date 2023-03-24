package org.indexmonitor.auth.application.services;

import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class OidcUserInfoService {

        public OidcUserInfo buildUserInfo(User user, Set<String> scopes) {
            return build(user,scopes);
        }


        private OidcUserInfo build(User user, Set<String> scopes){

            OidcUserInfo.Builder userInfoBuilder = OidcUserInfo.builder();

            if(scopes.contains(OidcScopes.OPENID))
            {
                userInfoBuilder.subject(user.getId().getValueAsString());
            }
            if(scopes.contains(OidcScopes.PROFILE))
            {
                userInfoBuilder.nickname(user.getUserName());
                userInfoBuilder.givenName(user.getProfile().getGivenName());
                userInfoBuilder.familyName(user.getProfile().getFamilyName());
                userInfoBuilder.name(user.getProfile().getGivenName() + " " + user.getProfile().getFamilyName());
            }
            if(scopes.contains(OidcScopes.EMAIL))
            {
                userInfoBuilder.email(user.getProfile().getEmail());
                userInfoBuilder.emailVerified(user.getProfile().isEmailConfirmed());
            }
            return userInfoBuilder.build();
        }
}
