package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserAuthoritiesUpdateRequest;

public interface UserAuthoritiesAddUseCase extends UseCase {
    BaseResponse add(UserAuthoritiesUpdateRequest request);
}
