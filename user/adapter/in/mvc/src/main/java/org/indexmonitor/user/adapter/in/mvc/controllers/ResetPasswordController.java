package org.indexmonitor.user.adapter.in.mvc.controllers;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.ResetPasswordUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordUpdateRequest;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordStartRequest;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordRegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("reset-password")
public class ResetPasswordController {

    private final ResetPasswordUseCase resetPasswordUseCase;

    public ResetPasswordController(ResetPasswordUseCase resetPasswordUseCase) {
        this.resetPasswordUseCase = resetPasswordUseCase;
    }

    @GetMapping("step-1")
    public String showEmailForm(Model model){
        model.addAttribute("startCommand", new ResetPasswordStartRequest());
        return "resetPassword/resetPasswordEmailForm";
    }

    @PostMapping("step-2")
    public String showQuestionForm(@Valid @ModelAttribute("startCommand") ResetPasswordStartRequest startCommand,
                                   BindingResult bindingResult, Model model, HttpSession session){
        if (bindingResult.hasErrors()) {
            return "resetPassword/resetPasswordEmailForm";
        }
        BaseResponse<ResetPasswordRegisterRequest> response = resetPasswordUseCase.start(startCommand);
        if(response.isFailure()){
            model.addAttribute("startCommand", new ResetPasswordStartRequest());
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/resetPasswordEmailForm";
        }

        model.addAttribute("email", response.getData().getEmail());
        model.addAttribute("question", response.getData().getRecoveryQuestion());
        session.setAttribute("command", response.getData());

        return "resetPassword/resetPasswordQuestionForm";
    }

    @PostMapping("step-3")
    public String resetPassword(@ModelAttribute("answer") String answer,
                                   BindingResult bindingResult, Model model, HttpSession session){

        ResetPasswordRegisterRequest command = (ResetPasswordRegisterRequest) session.getAttribute("command");
        command.setRecoveryQuestionAnswer(answer);
        BaseResponse response = resetPasswordUseCase.sendToken(command);
        if(response.isFailure()){
            model.addAttribute("email", command.getEmail());
            model.addAttribute("question", command.getRecoveryQuestion());
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/resetPasswordQuestionForm";
        }
        return "resetPassword/resetPasswordAccepted";
    }

    @GetMapping()
    public String validateToken(@RequestParam("token") String token,Model model, HttpSession session){
        BaseResponse response = resetPasswordUseCase.validateToken(token);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        session.setAttribute("token", token);
        model.addAttribute("newPassword", new ResetPasswordUpdateRequest());
        return "resetPassword/updatePasswordForm";
    }

    @PostMapping
    public String updatePassword(@Valid @ModelAttribute("newPassword") ResetPasswordUpdateRequest command, BindingResult bindingResult, HttpSession session, Model model){
        command.setToken((String)session.getAttribute("token"));
        if(!command.isValid()){
            command.setToken(null);
            return "resetPassword/updatePasswordForm";
        }
        BaseResponse response = resetPasswordUseCase.updatePassword(command);
        if(response.isFailure()){
            model.addAttribute("errorMessage", response.getMessage());
            return "resetPassword/updatePasswordForm";
        }
        return "redirect:/reset-password/success";
    }

    @GetMapping("success")
    public String success(Model model){
        model.addAttribute("millisecondLink", "4000");
        model.addAttribute("redirectLink", "http://localhost:8080/login");
        return "resetPassword/updatePasswordSuccess";
    }

}
