package org.indexmonitor.user.application.services;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailCompletedException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailNotFoundException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailTokenExpiredException;
import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailTokenFormatException;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailDeletePort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailLoadPort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailSendPort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailStorePort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmEmailService {

    @Value("${app.email.confirmEmail.tokenTimeToLiveInSeconds}")
    private Integer tokenLiveTimeInSeconds;
    @Value("${server.address}")
    private String host;
    @Value("${server.port}")
    private String port;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmEmailLoadPort confirmEmailLoadPort;
    private final ConfirmEmailStorePort confirmEmailStorePort;
    private final ConfirmEmailSendPort confirmEmailSendPort;
    private final ConfirmEmailDeletePort confirmEmailDeletePort;

    public ConfirmEmail send(User user, String redirectLink){
        ConfirmEmail confirmEmail = buildConfirmEmail(user, redirectLink);
        storeConfirmEmail(confirmEmail);
        sendConfirmEmailLink(confirmEmail);
        return confirmEmail;
    }

    public ConfirmEmail validate(String token){
        if(token == null || token.isEmpty()) {
            throw new ConfirmEmailTokenFormatException();
        }
        Optional<ConfirmEmail> confirmEmail = confirmEmailLoadPort.findByToken(new String(Base64.getDecoder().decode(token)));
        if(confirmEmail.isEmpty()){
            throw new ConfirmEmailNotFoundException();
        }
        if(confirmEmail.get().getToken().isExpired()){
            confirmEmailDeletePort.deleteById(confirmEmail.get().getId());
            throw new ConfirmEmailTokenExpiredException();
        }
        if(confirmEmail.get().getUser().getProfile().isEmailConfirmed()){
            confirmEmailDeletePort.deleteById(confirmEmail.get().getId());
            throw new ConfirmEmailCompletedException();
        }
        confirmEmailDeletePort.deleteById(confirmEmail.get().getId());
        return confirmEmail.get();
    }

    private ConfirmEmail buildConfirmEmail(User user, String redirectLink){
        Token token = Token.builder()
                        .tokenHash(passwordEncoder.encode(user.getId().getValueAsString() + UUID.randomUUID().toString()))
                        .algorithm(EncryptionAlgorithm.getDefaultAlgorithm())
                        .issuedAt(Instant.now())
                        .expireAt(Instant.now().plusSeconds(tokenLiveTimeInSeconds))
                        .build();
        String confirmEmailLink = String.format("%s:%s/confirm-email/callback?token=%s", host,port,token.getTokenEncoded());
        return new ConfirmEmail(user,token,confirmEmailLink,redirectLink);
    }

    private void storeConfirmEmail(ConfirmEmail model){
        if(confirmEmailLoadPort.findById(model.getId()).isPresent()){
            confirmEmailDeletePort.deleteById(model.getId());
        }
        confirmEmailStorePort.store(model);
    }

    private void sendConfirmEmailLink(ConfirmEmail confirmEmail){
        confirmEmailSendPort.sendConfirmEmail(confirmEmail);
    }

}
