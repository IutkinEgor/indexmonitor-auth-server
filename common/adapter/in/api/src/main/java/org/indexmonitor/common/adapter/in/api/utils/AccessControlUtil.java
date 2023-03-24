package org.indexmonitor.common.adapter.in.api.utils;

import org.indexmonitor.common.adapter.in.api.exceptions.AccessControlException;

import java.util.*;
import java.util.stream.Collectors;

public class AccessControlUtil {

    private AccessControlUtil() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Set<String> hasAllRoles = new HashSet<>();
        private Set<String> hasAllAuthorities = new HashSet<>();

        private Set<String> userRoles;
        private Set<String> userAuthorities;
        private Builder() {
            userRoles = new HashSet<>(OAuthExtractUtil.getRoles());
            userAuthorities = new HashSet<>(OAuthExtractUtil.getAuthorities());
        }

        public Builder hasAllRoles(Set<String> val) {
            hasAllRoles.addAll(val.stream().map(String::toUpperCase).collect(Collectors.toSet()));
            return this;
        }
        public Builder hasAllRoles(String... val) {
            hasAllRoles.addAll(Arrays.stream(val).map(String::toUpperCase).collect(Collectors.toSet()));
            return this;
        }
        public Builder hasAllAuthorities(Set<String> val) {
            hasAllAuthorities.addAll(val.stream().map(String::toUpperCase).collect(Collectors.toSet()));
            return this;
        }
        public Builder hasAllAuthorities(String... val) {
            hasAllAuthorities.addAll(Arrays.stream(val).map(String::toUpperCase).collect(Collectors.toSet()));
            return this;
        }

        public void validate() {
            if(!validateAccess()){
                throw new AccessControlException();
            }
        }
        public boolean validateAccess() {
            return validateAllRoles()  && validateAllAuthorities();
        }

        private boolean validateAllRoles(){
            var a = userRoles.containsAll(hasAllRoles);
            return a;
        }
//        private boolean validateAnyRoles(){
//            var a = !Collections.disjoint(OAuthExtractUtil.getRoles(),hasAnyRoles);
//            return a;
//        }
        private boolean validateAllAuthorities(){
            var a=  userAuthorities.containsAll(hasAllAuthorities);
            return a;
        }
    }
}
