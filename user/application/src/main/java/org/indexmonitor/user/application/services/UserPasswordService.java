//package org.indexmonitor.user.application.services;
//
//import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
//import org.indexmonitor.common.domain.exceptions.ApplicationException;
//import org.indexmonitor.common.domain.valueObjects.BaseId;
//import org.indexmonitor.common.domain.valueObjects.BaseResponse;
//import org.indexmonitor.user.application.exceptions.confirmEmailExceptions.ConfirmEmailTokenExpiredException;
//import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordNotFoundException;
//import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.ResetPasswordTokenFormatException;
//import org.indexmonitor.user.application.exceptions.resetPasswordExceptions.RecoveryAnswerException;
//import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
//import org.indexmonitor.user.application.ports.in.user.UserPasswordResetUseCase;
//import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;
//import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
//import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetStartRequest;
//import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
//import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordDeletePort;
//import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordLoadPort;
//import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordSendPort;
//import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordStorePort;
//import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
//import org.indexmonitor.user.domain.aggregates.ResetPassword;
//import org.indexmonitor.user.domain.aggregates.User;
//import org.indexmonitor.user.domain.valueObjects.Password;
//import org.indexmonitor.user.domain.valueObjects.Token;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.Base64;
//import java.util.Optional;
//import java.util.UUID;
//
//
//@Service
//@RequiredArgsConstructor
//public class UserPasswordService implements UserPasswordResetUseCase {
//
//    private final UserLoadPort userLoadPort;
//    private final UserUpdatePort userUpdatePort;
//    private final ResetPasswordLoadPort resetPasswordLoadPort;
//    private final ResetPasswordSendPort resetPasswordSendPort;
//    private final ResetPasswordStorePort resetPasswordStorePort;
//    private final ResetPasswordDeletePort resetPasswordDeletePort;
//    private final PasswordEncoder passwordEncoder;
//
//    @Value("${server.address}")
//    private String host;
//    @Value("${server.port}")
//    private String port;
//    @Override
//    public BaseResponse<UserPasswordResetRequest> start(UserPasswordResetStartRequest startCommand) {
//        try {
//            startCommand.validateSelf();
//            User user = tryRetrieveUser(startCommand.getEmail());
//            UserPasswordResetRequest command = new UserPasswordResetRequest();
//            command.setEmail(user.getProfile().getEmail());
//            command.setRecoveryQuestion(user.getProfile().getRecovery().getQuestion());
//            return new BaseResponse(command);
//        }catch (Exception e){
//            if(e instanceof ApplicationException){
//                return BaseResponse.failure(e.getMessage());
//            }
//            return BaseResponse.failure("Bad request");
//        }
//    }
//
//    @Override
//    public BaseResponse sendToken(UserPasswordResetRequest command) {
//        try {
//            command.validateSelf();
//            User user = tryRetrieveUser(command.getEmail());
//            validateQuestionAnswer(command,user);
//            ResetPassword resetPassword = buildResetPassword(user);
//            storeResetPassword(resetPassword);
//            sendResetPasswordLink(user.getProfile().getEmail(),getResetPasswordLink(resetPassword));
//        } catch (Exception e){
//            if(e instanceof ApplicationException){
//                return BaseResponse.failure(e.getMessage());
//            }
//            return BaseResponse.failure("Bad request");
//        }
//        return BaseResponse.success();
//    }
//
//    @Override
//    public BaseResponse validateToken(String token) {
//        try {
//            if(token == null || token.isEmpty()) {
//                throw new ResetPasswordTokenFormatException();
//            }
//            ResetPassword resetPassword = tryRetrieveResetPassword(new String(Base64.getDecoder().decode(token)));
//            if(resetPassword.getToken().isExpired()){
//                throw new ConfirmEmailTokenExpiredException();
//            }
//        }catch (Exception e){
//            if(e instanceof ApplicationException){
//                return BaseResponse.failure(e.getMessage());
//            }
//            return BaseResponse.failure("Bad request");
//        }
//      return BaseResponse.success();
//    }
//
//    @Override
//    public BaseResponse updatePassword(UserPasswordResetCallbackRequest command) {
//        try {
//            command.validateSelf();
//            ResetPassword resetPassword = tryRetrieveResetPassword(new String(Base64.getDecoder().decode(command.getToken())));
//            String encodedPassword = passwordEncoder.encode(command.getPassword());
//            Password password = new Password(encodedPassword, EncryptionAlgorithm.getDefaultAlgorithm());
//            User user = resetPassword.getUser().updatePassword(password);
//            resetPasswordDeletePort.deleteById(BaseId.map(resetPassword.getId()));
//            userUpdatePort.update(user);
//        }catch (Exception e){
//            if(e instanceof ApplicationException){
//                return BaseResponse.failure(e.getMessage());
//            }
//            return BaseResponse.failure("Bad request");
//        }
//        return BaseResponse.success();
//    }
//
//    private User tryRetrieveUser(String email){
//        Optional<User> user = userLoadPort.findByEmail(email);
//        if(user.isEmpty()){
//            throw new UserNotFoundException();
//        }
//        return user.get();
//    }
//
//    private ResetPassword tryRetrieveResetPassword(String decodedToken){
//        Optional<ResetPassword> resetPassword = resetPasswordLoadPort.findByToken(decodedToken);
//        if(resetPassword.isEmpty()){
//            throw new ResetPasswordNotFoundException();
//        }
//        return resetPassword.get();
//    }
//
//    private void validateQuestionAnswer(UserPasswordResetRequest command, User user){
//        if(!passwordEncoder.matches(command.getRecoveryQuestionAnswer(), user.getProfile().getRecovery().getAnswer())){
//            throw new RecoveryAnswerException();
//        }
//    }
//
//    private void sendResetPasswordLink(String email, String link){
//        resetPasswordSendPort.send(email,link);
//    }
//
//    private ResetPassword buildResetPassword(User user){
//        return  new ResetPassword(UUID.randomUUID(), buildResetPasswordToken(user),user);
//    }
//    private Token buildResetPasswordToken(User user){
//        return Token.builder()
//                .tokenHash(passwordEncoder.encode(user.getId().toString()))
//                .algorithm(EncryptionAlgorithm.getDefaultAlgorithm())
//                .issuedAt(Instant.now())
//                .expireAt(Instant.now().plusSeconds(600))
//                .build();
//    }
//
//    private void storeResetPassword(ResetPassword newModel){
//        Optional<ResetPassword> storedModel = resetPasswordLoadPort.findByUserId(newModel.getUser().getId());
//        if(storedModel.isPresent()){
//            resetPasswordDeletePort.deleteById(BaseId.map(storedModel.get().getId()));
//        }
//        resetPasswordStorePort.store(newModel);
//    }
//
//    private String getResetPasswordLink(ResetPassword resetPassword){
//
//        return String.format("http://%s:%s/reset-password?token=%s",host,port,resetPassword.getToken().getTokenEncoded());
////        return "http://localhost:8080/reset-password?token="+ resetPassword.getToken().getTokenEncoded();
//    }
//}
