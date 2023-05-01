package org.indexmonitor.user.application.services;

import lombok.RequiredArgsConstructor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.RecoveryAnswerException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordNotFoundException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordTokenExpiredException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordTokenFormatException;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordDeletePort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordLoadPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordSendPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordStorePort;
import org.indexmonitor.user.domain.aggregates.ResetPassword;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPasswordResetService {
    @Value("${app.email.resetPassword.tokenTimeToLiveInSeconds}")
    private Integer tokenLiveTimeInSeconds;
    @Value("${app.email.externalDomainAddress}")
    private String externalDomainAddress;

    private final ResetPasswordLoadPort resetPasswordLoadPort;
    private final ResetPasswordSendPort resetPasswordSendPort;
    private final ResetPasswordStorePort resetPasswordStorePort;
    private final ResetPasswordDeletePort resetPasswordDeletePort;
    private final PasswordEncoder passwordEncoder;

    public void validateAnswerAndSendRecoveryLink(UserPasswordResetRequest request, User user){
        if(!passwordEncoder.matches(request.getRecoveryQuestionAnswer(), user.getProfile().getRecovery().getAnswer())){
            throw new RecoveryAnswerException();
        }
        ResetPassword resetPassword = buildResetPassword(request,user);
        storeResetPassword(resetPassword);
        sendResetPasswordLink(resetPassword);
    }

    public BaseId validateCallbackAndReturnUserId(String token){
        if(token == null || token.isEmpty()) {
            throw new ResetPasswordTokenFormatException();
        }
        Optional<ResetPassword> resetPassword = resetPasswordLoadPort.findByToken(new String(Base64.getDecoder().decode(token)));
        if(resetPassword.isEmpty()){
            throw new ResetPasswordNotFoundException();
        }
        if(resetPassword.get().getToken().isExpired()){
            resetPasswordDeletePort.deleteById(resetPassword.get().getId());
            throw new ResetPasswordTokenExpiredException();
        }
        //resetPasswordDeletePort.deleteById(resetPassword.get().getId());
        return resetPassword.get().getUser().getId();
    }

    public String removeTokenAndReturnRedirectLink(BaseId id){
         Optional<ResetPassword> resetPassword = resetPasswordLoadPort.findByUserId(id);
        if(resetPassword.isEmpty()){
            throw new ResetPasswordNotFoundException();
        }
        if(resetPassword.get().getToken().isExpired()){
            resetPasswordDeletePort.deleteById(resetPassword.get().getId());
            throw new ResetPasswordTokenExpiredException();
        }
        resetPasswordDeletePort.deleteById(resetPassword.get().getId());
        return resetPassword.get().getRedirectLink();
    }

    private ResetPassword buildResetPassword(UserPasswordResetRequest request, User user){
        Token token = Token.builder()
                .tokenHash(passwordEncoder.encode(user.getId().getValueAsString() + UUID.randomUUID().toString()))
                .algorithm(EncryptionAlgorithm.getDefaultAlgorithm())
                .issuedAt(Instant.now())
                .expireAt(Instant.now().plusSeconds(tokenLiveTimeInSeconds))
                .build();
        String confirmEmailLink = String.format("%s/reset-password/callback?token=%s", externalDomainAddress,token.getTokenEncoded());
        return new ResetPassword(user,token,confirmEmailLink, request.getRedirectLink());
    }

    private void storeResetPassword(ResetPassword resetPassword){
        if(resetPasswordLoadPort.findById(resetPassword.getId()).isPresent()){
            resetPasswordDeletePort.deleteById(resetPassword.getId());
        }
        resetPasswordStorePort.store(resetPassword);
    }

    private void sendResetPasswordLink(ResetPassword resetPassword){
        resetPasswordSendPort.send(resetPassword);
    }
}
