package org.indexmonitor.user.adapter.out.email.services;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.indexmonitor.user.adapter.out.email.exceptions.EmailException;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordSendPort;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Duration;

@Component
@RequiredArgsConstructor
class ChangePasswordSendService implements ChangePasswordSendPort {

    @Value("${app.email.from}")
    private String from;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void send(ChangePassword changePassword){
        Context context = new Context();
        context.setVariable("name", changePassword.getUser().getProfile().getName());
        context.setVariable("changePasswordLink", changePassword.getConfirmLink());
        context.setVariable("linkLiveTime", Duration.between(changePassword.getToken().getIssuedAt(), changePassword.getToken().getExpireAt()).toMinutes());

        String process = templateEngine.process("changePassword", context);

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(from);
            helper.setTo(changePassword.getUser().getProfile().getEmail());
            helper.setSubject("Reset Password");
            helper.setText(process, true);

            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new EmailException();
        }
    }
}
