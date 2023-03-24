package org.indexmonitor.user.adapter.in.mvc.advices;

import org.indexmonitor.common.domain.exceptions.ApplicationException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailCompletedException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailTokenExpiredException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailTokenFormatException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConfirmEmailAdvice {

    @ExceptionHandler({ ConfirmEmailCompletedException.class, ConfirmEmailTokenExpiredException.class, ConfirmEmailTokenFormatException.class})
    public String confirmEmailCompletedException(Model model, ApplicationException exception){

        model.addAttribute("errorMessage", exception.getMessage());
        return "/register/confirmEmailError";
    }
}
