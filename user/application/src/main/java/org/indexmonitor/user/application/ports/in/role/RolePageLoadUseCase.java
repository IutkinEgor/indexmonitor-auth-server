package org.indexmonitor.user.application.ports.in.role;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RolePageLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RolePageResponse;

public interface RolePageLoadUseCase extends UseCase {
    BasePageResponse<RolePageResponse> load(RolePageLoadRequest request);
}
