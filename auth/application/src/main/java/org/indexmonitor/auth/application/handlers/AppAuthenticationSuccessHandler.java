package org.indexmonitor.auth.application.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${app.issuer_url}")
    private String issuer_url;

    private RequestCache requestCache = new HttpSessionRequestCache();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            this.requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        clearAuthenticationAttributes(request);

        UriComponentsBuilder redirectUrlBuilder = UriComponentsBuilder.fromUriString(savedRequest.getRedirectUrl());

        UriComponentsBuilder targetUrlBuilder = UriComponentsBuilder.fromUriString(issuer_url);
        targetUrlBuilder.path(redirectUrlBuilder.build().getPath());
        targetUrlBuilder.replaceQuery(redirectUrlBuilder.build().getQuery());
        getRedirectStrategy().sendRedirect(request, response, targetUrlBuilder.build().toUriString());
    }
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
