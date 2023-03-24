package org.indexmonitor.auth.application.filters;

import org.indexmonitor.auth.application.authenticationToken.EmailPasswordAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class EmailPasswordAuthenticationFilter extends OncePerRequestFilter  {

    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = "principal";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login","POST");
    private final String emailParameter = SPRING_SECURITY_FORM_EMAIL_KEY;
    private final String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private final AuthenticationManager authenticationManager;

    private final boolean postOnly = true;

    public EmailPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!isAuthenticated(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = obtainEmail(request);
        email = (email != null) ? email.trim() : "";
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        EmailPasswordAuthenticationToken unauthenticated = EmailPasswordAuthenticationToken.unauthenticated(email,password);
        try {
            Authentication authenticationResult = this.authenticationManager.authenticate(unauthenticated);
            if(authenticationResult.isAuthenticated()) {
                this.onAuthenticationSuccess(request,response,authenticationResult);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(LogMessage.format("Client authentication failed: %s", e.getMessage()));
            }
            this.onAuthenticationFailure(request,response,e);
            filterChain.doFilter(request, response);
        }

    }


    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }
    protected String obtainEmail(HttpServletRequest request) {
        return request.getParameter(this.emailParameter);
    }

    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request)) {
            return true;
        }
        if (this.logger.isTraceEnabled()) {
            this.logger
                    .trace(LogMessage.format("Did not match request to %s", DEFAULT_ANT_PATH_REQUEST_MATCHER));
        }
        return false;
    }

    protected boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() != null){
            if(context.getAuthentication().isAuthenticated()){
                if (this.logger.isTraceEnabled()) {
                    this.logger
                            .trace(LogMessage.format("Already authenticated %s", DEFAULT_ANT_PATH_REQUEST_MATCHER));
                }
                return true;
            }
        }
        return false;
    }

    private void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) {

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Set SecurityContextHolder authentication to %s",
                    authentication.getClass().getSimpleName()));
        }
    }

    private void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                         Exception exception) throws IOException {
        SecurityContextHolder.clearContext();
    }

}
