package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserAuthoritiesResponse;

import java.util.Set;

public interface UserAuthoritiesLoadUseCase extends UseCase {
    BaseResponse<Set<UserAuthoritiesResponse>> load(UserLoadRequest request);
}
