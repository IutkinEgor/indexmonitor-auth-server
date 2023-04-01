package org.indexmonitor.auth.application.services;

import org.indexmonitor.auth.application.models.UserAccountDetails;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class OidcUserInfoService {

        public OidcUserInfo buildUserInfo(UserAccountDetails user, Set<String> scopes) {
            return build(user,scopes);
        }

        private OidcUserInfo build(UserAccountDetails user, Set<String> scopes){

            OidcUserInfo.Builder userInfoBuilder = OidcUserInfo.builder();

            if(scopes.contains(OidcScopes.OPENID))
            {
                userInfoBuilder.subject(user.getUserId());
            }
            if(scopes.contains(OidcScopes.PROFILE))
            {
                userInfoBuilder.nickname(user.getUsername());
                userInfoBuilder.givenName(user.getGivenName());
                userInfoBuilder.familyName(user.getFamilyName());
                userInfoBuilder.name(user.getGivenName() + " " + user.getFamilyName());
            }
            if(scopes.contains(OidcScopes.EMAIL))
            {
                userInfoBuilder.email(user.getEmail());
            }
            return userInfoBuilder.build();
        }
}
