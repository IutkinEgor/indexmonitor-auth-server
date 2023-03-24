package org.indexmonitor.user.adapter.in.mvc.advices;

import org.indexmonitor.user.application.exceptions.userExceptions.UserExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterAdvice {

    @ExceptionHandler(UserExistException.class)
    public String handleUserAccountExistException(Model model, UserExistException e){

        model.addAttribute("registerUserAccountCommand", e.getRegisterUserCommand());
        model.addAttribute("userExistAlert", e.getMessage());

        return "register/registerForm";
    }
}
