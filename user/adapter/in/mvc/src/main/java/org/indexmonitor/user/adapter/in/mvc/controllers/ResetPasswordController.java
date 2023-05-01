package org.indexmonitor.user.adapter.in.mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserPasswordResetUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetStartRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import org.indexmonitor.user.application.ports.in.user.responses.UserPasswordResetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("reset-password")
@RequiredArgsConstructor
public class ResetPasswordController {

    @Value("${app.resetPasswordRedirectUrl}")
    private String resetPasswordRedirectUrl;
    private final UserPasswordResetUseCase userPasswordResetUseCase;

    @GetMapping("step-1")
    public String showEmailForm(@RequestParam(value = "redirectUrl", required = false) String redirectUrl, Model model, HttpSession session){
        session.setAttribute("redirectUrl", redirectUrl == null || redirectUrl.isEmpty() ? resetPasswordRedirectUrl : redirectUrl);
        model.addAttribute("request", new UserPasswordResetStartRequest());
        return "resetPassword/resetPasswordEmailForm";
    }

    @PostMapping("step-2")
    public String showQuestionForm(@Valid @ModelAttribute("request") UserPasswordResetStartRequest request,
                                   BindingResult bindingResult, Model model, HttpSession session){
        if (bindingResult.hasErrors()) {
            return "resetPassword/resetPasswordEmailForm";
        }
        BaseResponse<UserPasswordResetResponse> response = userPasswordResetUseCase.loadUserInfo(request);
        if(response.isFailure()){
            model.addAttribute("startCommand", new UserPasswordResetStartRequest());
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/resetPasswordEmailForm";
        }

        model.addAttribute("email", response.getData().getEmail());
        model.addAttribute("question", response.getData().getRecoveryQuestion());
        session.setAttribute("response", response.getData());
        return "resetPassword/resetPasswordQuestionForm";
    }

    @PostMapping("step-3")
    public String resetPassword(@ModelAttribute("answer") String answer, Model model, HttpSession session){
        UserPasswordResetResponse previousResponse = (UserPasswordResetResponse) session.getAttribute("response");
        String redirectUrl = session.getAttribute("redirectUrl").toString();
        UserPasswordResetRequest request = new UserPasswordResetRequest(previousResponse.getEmail(),previousResponse.getRecoveryQuestion(),answer, redirectUrl);
        BaseResponse response = userPasswordResetUseCase.sendToken(request);
        if(response.isFailure()){
            model.addAttribute("email", request.getEmail());
            model.addAttribute("question", request.getRecoveryQuestion());
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/resetPasswordQuestionForm";
        }
        return "resetPassword/resetPasswordAccepted";
    }

    @GetMapping("callback")
    public String validateToken(@RequestParam("token") String token,Model model, HttpSession session){
        BaseResponse<BaseId> response = userPasswordResetUseCase.validateToken(token);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        session.setAttribute("userId", response.getData().getValueAsString());
        model.addAttribute("newPassword", new UserPasswordResetCallbackRequest());
        return "resetPassword/updatePasswordForm";
    }

    @PostMapping
    public String updatePassword(@Valid @ModelAttribute("newPassword") UserPasswordResetCallbackRequest request, BindingResult bindingResult, HttpSession session, Model model){
        request.setUserId((String)session.getAttribute("userId"));
        if(!request.isValid()){
            request.setUserId(null);
            return "resetPassword/updatePasswordForm";
        }
        BaseResponse<RedirectUrlResponse> response = userPasswordResetUseCase.updatePassword(request);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/updatePasswordForm";
        }
        model.addAttribute("millisecondLink", "3000");
        model.addAttribute("redirectLink", response.getData().getUrl());
        return "resetPassword/updatePasswordSuccess";
    }
}
