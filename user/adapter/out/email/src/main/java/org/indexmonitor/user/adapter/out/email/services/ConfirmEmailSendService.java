package org.indexmonitor.user.adapter.out.email.services;

import org.indexmonitor.user.adapter.out.email.exceptions.EmailException;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailSendPort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
class ConfirmEmailSendService implements ConfirmEmailSendPort {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendConfirmEmail(ConfirmEmail confirmEmail){
        Context context = new Context();
        context.setVariable("name", confirmEmail.getUser().getProfile().getName());
        context.setVariable("confirmLink", confirmEmail.getConfirmLink());

        String process = templateEngine.process("confirmEmail", context);

        try{
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

        System.out.println("Confirm email link: " + confirmEmail.getConfirmLink());
    }
}
