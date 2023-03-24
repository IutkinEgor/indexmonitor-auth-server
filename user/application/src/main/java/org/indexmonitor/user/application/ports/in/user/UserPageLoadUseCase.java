package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserPageLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserPageResponse;

public interface UserPageLoadUseCase extends UseCase {
    BasePageResponse<UserPageResponse> load(UserPageLoadRequest request);
}
