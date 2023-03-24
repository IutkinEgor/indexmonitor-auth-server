package org.indexmonitor.auth.application.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

//@Component
//@AllArgsConstructor
public class AppAuthenticationFailureHandler implements AuthenticationFailureHandler {

  //  private final AppUserDetailsService userAccountDetailsService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception.getClass().equals(DisabledException.class)){

        }




        System.out.println("AppAuthenticationFailureHandler: " + exception.getMessage());


    }




}
