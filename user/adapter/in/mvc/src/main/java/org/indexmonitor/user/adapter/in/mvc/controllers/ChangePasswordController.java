package org.indexmonitor.user.adapter.in.mvc.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserLoginUseCase;
import org.indexmonitor.user.application.ports.in.user.UserPasswordChangeUseCase;
import org.indexmonitor.user.application.ports.in.user.UserProfileLoadUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.*;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import org.indexmonitor.user.application.ports.in.user.responses.UserProfileResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("change-password")
@RequiredArgsConstructor
public class ChangePasswordController {

    private final UserProfileLoadUseCase userProfileLoadUseCase;
    private final UserPasswordChangeUseCase userPasswordChangeUseCase;

    @GetMapping("{id}")
    public String showForm(@PathVariable(value = "id") String id,  @RequestParam(value = "redirectUrl", required = false) String redirectUrl, Model model, HttpSession session){
        session.setAttribute("redirectUrl", redirectUrl);
        BaseResponse<UserProfileResponse> response = userProfileLoadUseCase.load(new UserLoadRequest(id));
        if(response.isFailure()){
            return "error";
        }
        model.addAttribute("email", response.getData().getEmail());
        return "changePassword/changePasswordEmailForm";
    }

    @PostMapping("credentials")
    public String verifyCredentials(@ModelAttribute("email") String email, @ModelAttribute("password") String password, Model model, HttpSession session){
        String redirectUrl = session.getAttribute("redirectUrl").toString();
        BaseResponse response = userPasswordChangeUseCase.verifyCredentials(new UserPasswordChangeRequest(email,password,redirectUrl));
        if(response.isFailure()){
            model.addAttribute("email", email);
            model.addAttribute("errorMessage", response.getMessage());
            return "changePassword/changePasswordEmailForm";
        }
        return "changePassword/changePasswordAccepted";
    }

    @GetMapping("callback")
    public String validateToken(@RequestParam("token") String token,Model model, HttpSession session){
        BaseResponse<BaseId> response = userPasswordChangeUseCase.validateToken(token);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        session.setAttribute("userId", response.getData().getValueAsString());
        model.addAttribute("newPassword", new UserPasswordChangeCallbackRequest());
        return "changePassword/updatePasswordForm";
    }

    @PostMapping
    public String updatePassword(@Valid @ModelAttribute("newPassword") UserPasswordChangeCallbackRequest request, BindingResult bindingResult, HttpSession session, Model model){
        request.setUserId((String)session.getAttribute("userId"));
        if(!request.isValid()){
            request.setUserId(null);
            return "changePassword/updatePasswordForm";
        }
        BaseResponse<RedirectUrlResponse> response = userPasswordChangeUseCase.updatePassword(request);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "changePassword/updatePasswordForm";
        }
        model.addAttribute("millisecondLink", "3000");
        model.addAttribute("redirectLink", response.getData().getUrl());
        return "changePassword/updatePasswordSuccess";
    }
}
