package org.indexmonitor.user.application.interactors.authority;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityExistException;
import org.indexmonitor.user.application.mappers.AuthorityMapper;
import org.indexmonitor.user.application.ports.in.authority.AuthorityRegisterUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityRegisterRequest;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.application.ports.out.authority.AuthorityRegisterPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class AuthorityRegisterInteractor extends Interactor implements AuthorityRegisterUseCase {
    private final Logger logger = LoggerFactory.getLogger(AuthorityRegisterInteractor.class);
    private final AuthorityRegisterPort authorityRegisterPort;
    private final AuthorityLoadPort authorityLoadPort;
    private final AuthorityMapper authorityMapper;

    @Override
    public BaseResponse register(AuthorityRegisterRequest request) {
        try{
            request.validateSelf();
            Authority authority = buildAuthority(request);
            tryRegisterAuthority(authority);
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to register authority. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private Authority buildAuthority(AuthorityRegisterRequest request){
        return authorityMapper.mapRegisterRequest(request, authorityRegisterPort.generateId());
    }

    private void tryRegisterAuthority(Authority authority){
        if(authorityLoadPort.isExistByName(authority.getName())){
            throw new AuthorityExistException();
        }
        authorityRegisterPort.register(authority);
    }
}
