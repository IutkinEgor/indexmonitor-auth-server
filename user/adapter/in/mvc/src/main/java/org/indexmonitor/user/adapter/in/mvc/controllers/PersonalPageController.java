package org.indexmonitor.user.adapter.in.mvc.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetStartRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("personal")
@AllArgsConstructor
public class PersonalPageController {

    @GetMapping
    public String showInfo(Model model, HttpSession session){
        var context = SecurityContextHolder.getContext();
        return "personal/personal";
    }
}
