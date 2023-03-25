package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserExistException;
import org.indexmonitor.user.application.mappers.UserMapper;
import org.indexmonitor.user.application.ports.in.user.UserAuthoritiesAddUseCase;
import org.indexmonitor.user.application.ports.in.user.UserRegisterManualUseCase;
import org.indexmonitor.user.application.ports.in.user.UserRolesAddUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserAuthoritiesUpdateRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterManualRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserRolesUpdateRequest;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserRegisterPort;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.Recovery;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserRegisterManualInteractor extends Interactor implements UserRegisterManualUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserRegisterManualInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserRegisterPort userRegisterPort;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesAddUseCase userRolesAddUseCase;
    private final UserAuthoritiesAddUseCase userAuthoritiesAddUseCase;

    @Override
    public BaseResponse register(UserRegisterManualRequest request) {
        try {
            request.validateSelf();
            User user = buildUser(request);
            tryRegisterUser(user);
            tryAddRoles(user, request);
            tryAddAuthorities(user,request);
            return onRequestSuccess();
        }
        catch (Exception e){
            logger.debug(String.format("Failed to manual register user. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private User buildUser(UserRegisterManualRequest request){
        BaseId userId = userRegisterPort.getNewId();
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Password password = new Password(encodedPassword, EncryptionAlgorithm.getDefaultAlgorithm());
        String encodedAnswer = passwordEncoder.encode(userId.getValueAsString());
        Recovery recovery = new Recovery("Generated recovery answer.",encodedAnswer,EncryptionAlgorithm.getDefaultAlgorithm());
        return mapper.mapRegisterManualRequest(request, userId, password, recovery);
    }

    private void tryRegisterUser(User user){
        if(userLoadPort.isExist(user)){
            throw new UserExistException();
        }
        userRegisterPort.register(user);
    }

    private void tryAddRoles(User user, UserRegisterManualRequest request){
        if(request.getRoleIds() == null || request.getRoleIds().isEmpty()){
            return;
        }
        userRolesAddUseCase.add(new UserRolesUpdateRequest(user.getId().getValueAsString(), request.getRoleIds()));
    }

    private void tryAddAuthorities(User user, UserRegisterManualRequest request){
        if(request.getAuthorityIds() == null || request.getAuthorityIds().isEmpty()){
            return;
        }
        userAuthoritiesAddUseCase.add(new UserAuthoritiesUpdateRequest(user.getId().getValueAsString(), request.getAuthorityIds()));
    }

}
