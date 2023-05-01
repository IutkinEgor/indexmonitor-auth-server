package org.indexmonitor.user.adapter.out.email.services;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.indexmonitor.user.adapter.out.email.exceptions.EmailException;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordSendPort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import org.indexmonitor.user.domain.aggregates.ResetPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Duration;

@Service
@RequiredArgsConstructor
class ResetPasswordSendService implements ResetPasswordSendPort {

    @Value("${app.email.from}")
    private String from;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void send(ResetPassword resetPassword){
        Context context = new Context();
        context.setVariable("name", resetPassword.getUser().getProfile().getName());
        context.setVariable("resetPasswordLink", resetPassword.getConfirmLink());
        context.setVariable("linkLiveTime", Duration.between(resetPassword.getToken().getIssuedAt(), resetPassword.getToken().getExpireAt()).toMinutes());

        String process = templateEngine.process("resetPassword", context);

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(from);
            helper.setTo(resetPassword.getUser().getProfile().getEmail());
            helper.setSubject("Reset Password");
            helper.setText(process, true);

            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new EmailException();
        }
    }
}
