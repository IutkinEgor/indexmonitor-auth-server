package org.indexmonitor.user.adapter.in.mvc.controllers;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserRegisterUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;
import org.indexmonitor.user.domain.enums.RecoveryQuestions;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class UserRegisterController {

    @Value("${app.email.enable}")
    private Boolean isEmailServiceEnable;
    private final UserRegisterUseCase userRegisterUseCase;


    @GetMapping
    public String showRegistrationForm(@RequestParam("redirectUrl") String redirectUrl, Model model, HttpSession session) {
        session.setAttribute("redirectUrl", redirectUrl);
        model.addAttribute("registerUserAccountCommand", new UserRegisterRequest(redirectUrl));
        model.addAttribute("recoveryQuestion", RecoveryQuestions.values());
        return "register/registerForm";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("registerUserAccountCommand") UserRegisterRequest request,
                           BindingResult bindingResult, Model model, HttpSession session) {
        String redirectUrl = session.getAttribute("redirectUrl").toString();
        request.setRedirectUrl(redirectUrl);
        if (bindingResult.hasErrors()) {
            return "register/registerForm";
        }
        BaseResponse response = this.userRegisterUseCase.register(request);
        if(response.isFailure()){
            request.setPassword(null);
            request.setConfirmPassword(null);
            model.addAttribute("registerUserAccountCommand", request);
            model.addAttribute("recoveryQuestion", RecoveryQuestions.values());
            model.addAttribute("errorMessage", response.getMessage());
            return "register/registerForm";
        }
        if(isEmailServiceEnable){
            return "confirmEmail/confirmEmail";
        }
        model.addAttribute("millisecondLink", "3000");
        model.addAttribute("redirectLink", redirectUrl);
        return "register/registerSuccess";
    }
}
