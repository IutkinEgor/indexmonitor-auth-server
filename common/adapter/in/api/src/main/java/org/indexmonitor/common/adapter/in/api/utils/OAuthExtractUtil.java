package org.indexmonitor.common.adapter.in.api.utils;

import org.indexmonitor.common.adapter.in.api.exceptions.OAuthSubException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

public class OAuthExtractUtil {
    public static String getSub(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isAuthenticationValid(authentication);
        Jwt jwt = (Jwt)authentication.getPrincipal();
        var a =jwt.getClaims().get("sub");
        return jwt.getClaims().get("sub").toString();
    }

    public static List<String> getRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isAuthenticationValid(authentication);
        Jwt jwt = (Jwt)authentication.getPrincipal();
        Object roleObject = jwt.getClaims().get("roles");
        List<String> roles = new ArrayList<>();
        if(roleObject instanceof List<?>) {
            roles = (List<String>) roleObject;
        }
        return roles;
    }

    public static List<String> getAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isAuthenticationValid(authentication);
        Jwt jwt = (Jwt)authentication.getPrincipal();
        Object authorityObject = jwt.getClaims().get("authorities");
        List<String> authorities = new ArrayList<>();
        if(authorityObject instanceof List<?>) {
            authorities = (List<String>) authorityObject;
        }
        return authorities;
    }

    private static void isAuthenticationValid(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new OAuthSubException("Not authorized");
        }
        if(!(authentication instanceof JwtAuthenticationToken)) {
            throw new OAuthSubException("Authentication is not JwtAuthenticationToken instance.");
        }
    }



}

