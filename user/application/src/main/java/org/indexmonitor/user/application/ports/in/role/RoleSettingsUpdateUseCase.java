package org.indexmonitor.user.application.ports.in.role;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsUpdateRequest;

public interface RoleSettingsUpdateUseCase extends UseCase {
    BaseResponse update(RoleSettingsUpdateRequest request);
}
