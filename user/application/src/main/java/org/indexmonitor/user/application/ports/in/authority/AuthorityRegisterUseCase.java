package org.indexmonitor.user.application.ports.in.authority;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityRegisterRequest;

public interface AuthorityRegisterUseCase extends UseCase {
    BaseResponse register(AuthorityRegisterRequest request);
}
