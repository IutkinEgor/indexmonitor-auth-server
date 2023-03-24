package org.indexmonitor.user.adapter.in.mvc.controllers;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserLoginUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginUserAccountController {

    private final UserLoginUseCase loginEmailUseCase;

    public LoginUserAccountController(UserLoginUseCase loginEmailUseCase) {
        this.loginEmailUseCase = loginEmailUseCase;
    }

    @GetMapping
    public String showLoginForm(Model model, HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
        if(error != null){
            HttpSession session = request.getSession();
            String errorMessage = null;
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (ex != null) {
                    errorMessage = ex.getMessage();
                }
            }
            model.addAttribute("errorMessage", errorMessage);
        }
        model.addAttribute("loginEmailCommand", new UserLoginRequest());
        return "login/loginForm";
    }

//    @GetMapping
//    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error) {
//        if(error != null){
//            model.addAttribute("error", error);
//        }
//        model.addAttribute("loginEmailCommand", new UserLoginRequest());
//        return "login/loginForm";
//    }

    @PostMapping
    public String login(@Valid @ModelAttribute("loginEmailCommand") UserLoginRequest command,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        BaseResponse response = this.loginEmailUseCase.loginEmail(command);
        return "redirect:/";
    }


}
