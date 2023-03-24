package org.indexmonitor.user.adapter.in.api.controllers;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/email")
public class MailSendController {

    @GetMapping
    public ResponseEntity<BaseResponse> send(){
        return ResponseEntity.ok().build();
    }
}
