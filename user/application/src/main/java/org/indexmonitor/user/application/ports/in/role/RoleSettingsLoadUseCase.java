package org.indexmonitor.user.application.ports.in.role;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RoleResponse;

public interface RoleSettingsLoadUseCase extends UseCase {
    BaseResponse<RoleResponse> load(RoleSettingsLoadRequest request);
}
