package org.indexmonitor.user.application.services;

import lombok.RequiredArgsConstructor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.RecoveryAnswerException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordNotFoundException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordTokenExpiredException;
import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordTokenFormatException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserCredentialsException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordChangeRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordDeletePort;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordLoadPort;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordSendPort;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordStorePort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordDeletePort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordLoadPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordSendPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordStorePort;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
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
public class UserPasswordChangeService {
    @Value("${app.email.resetPassword.tokenTimeToLiveInSeconds}")
    private Integer tokenLiveTimeInSeconds;
    @Value("${app.email.externalDomainAddress}")
    private String externalDomainAddress;

    private final ChangePasswordLoadPort changePasswordLoadPort;
    private final ChangePasswordSendPort changePasswordSendPort;
    private final ChangePasswordStorePort changePasswordStorePort;
    private final ChangePasswordDeletePort changePasswordDeletePort;
    private final PasswordEncoder passwordEncoder;

    public void sendRecoveryLink(UserPasswordChangeRequest request, User user){
        ChangePassword changePassword = buildChangePassword(request, user);
        storeChangePassword(changePassword);
        sendResetPasswordLink(changePassword);
    }

    public BaseId validateCallbackAndReturnUserId(String token){
        if(token == null || token.isEmpty()) {
            throw new ResetPasswordTokenFormatException();
        }
        Optional<ChangePassword> resetPassword = changePasswordLoadPort.findByToken(new String(Base64.getDecoder().decode(token)));
        if(resetPassword.isEmpty()){
            throw new ResetPasswordNotFoundException();
        }
        if(resetPassword.get().getToken().isExpired()){
            changePasswordDeletePort.deleteById(resetPassword.get().getId());
            throw new ResetPasswordTokenExpiredException();
        }
        return resetPassword.get().getUser().getId();
    }

    public String removeTokenAndReturnRedirectLink(BaseId id){
        Optional<ChangePassword> changePassword = changePasswordLoadPort.findByUserId(id);
        if(changePassword.isEmpty()){
            throw new ResetPasswordNotFoundException();
        }
        if(changePassword.get().getToken().isExpired()){
            changePasswordDeletePort.deleteById(changePassword.get().getId());
            throw new ResetPasswordTokenExpiredException();
        }
        changePasswordDeletePort.deleteById(changePassword.get().getId());
        return changePassword.get().getRedirectLink();
    }

    private ChangePassword buildChangePassword(UserPasswordChangeRequest request, User user){
        Token token = Token.builder()
                .tokenHash(passwordEncoder.encode(user.getId().getValueAsString() + UUID.randomUUID().toString()))
                .algorithm(EncryptionAlgorithm.getDefaultAlgorithm())
                .issuedAt(Instant.now())
                .expireAt(Instant.now().plusSeconds(tokenLiveTimeInSeconds))
                .build();
        String confirmEmailLink = String.format("%s/change-password/callback?token=%s", externalDomainAddress,token.getTokenEncoded());
        return new ChangePassword(user,token,confirmEmailLink, request.getRedirectLink());
    }

    private void storeChangePassword(ChangePassword changePassword){
        if(changePasswordLoadPort.findById(changePassword.getId()).isPresent()){
            changePasswordDeletePort.deleteById(changePassword.getId());
        }
        changePasswordStorePort.store(changePassword);
    }

    private void sendResetPasswordLink(ChangePassword changePassword){
        changePasswordSendPort.send(changePassword);
    }
}
