package org.indexmonitor.user.adapter.out.email.services;

import org.indexmonitor.user.adapter.out.email.exceptions.EmailException;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailSendPort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Duration;

@Service
@AllArgsConstructor
class ConfirmEmailSendService implements ConfirmEmailSendPort {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void send(ConfirmEmail confirmEmail){
        Context context = new Context();
        context.setVariable("name", confirmEmail.getUser().getProfile().getName());
        context.setVariable("confirmLink", confirmEmail.getConfirmLink());
        context.setVariable("linkLiveTime", Duration.between(confirmEmail.getToken().getIssuedAt(), confirmEmail.getToken().getExpireAt()).toHours());

        String process = templateEngine.process("confirmEmail", context);

        try {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("spring.mail.username");
        helper.setTo(confirmEmail.getUser().getProfile().getEmail());
        helper.setSubject("Welcome");
        helper.setText(process, true);
        javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new EmailException();
        }
    }
}
