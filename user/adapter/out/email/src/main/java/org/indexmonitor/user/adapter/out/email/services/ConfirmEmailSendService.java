package org.indexmonitor.user.adapter.out.email.services;

import lombok.RequiredArgsConstructor;
import org.indexmonitor.user.adapter.out.email.exceptions.EmailException;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailSendPort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
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
class ConfirmEmailSendService implements ConfirmEmailSendPort {

    @Value("${app.email.from}")
    private String from;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void send(ConfirmEmail confirmEmail){
        Context context = new Context();
        context.setVariable("name", confirmEmail.getUser().getProfile().getName());
        context.setVariable("confirmLink", confirmEmail.getConfirmLink());
        context.setVariable("linkLiveTime", Duration.between(confirmEmail.getToken().getIssuedAt(), confirmEmail.getToken().getExpireAt()).toMinutes());

        String process = templateEngine.process("confirmEmail", context);

        try {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(from);
        helper.setTo(confirmEmail.getUser().getProfile().getEmail());
        helper.setSubject("Confirm Email");
        helper.setText(process, true);
        javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new EmailException();
        }
    }
}
