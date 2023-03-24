package org.indexmonitor.user.adapter.in.mvc.controllers;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserRegisterUseCase;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("confirm-email")
@AllArgsConstructor
public class ConfirmEmailController {
    private final UserRegisterUseCase userRegisterUseCase;

    @GetMapping
    public String confirmEmail(){
        return "confirmEmail/confirmEmail";
    }

    @GetMapping("callback")
    public String confirmEmailCallback(@RequestParam("token") String token, Model model){
        BaseResponse<RedirectUrlResponse> response = this.userRegisterUseCase.confirmEmailCallback(token);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        model.addAttribute("millisecondLink", "4000");
        model.addAttribute("redirectLink", response.getData().getUrl());
        return "confirmEmail/confirmEmailSuccess";
    }
}
