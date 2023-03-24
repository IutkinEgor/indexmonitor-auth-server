package org.indexmonitor.user.application.ports.in.authority;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityResponse;


public interface AuthoritySettingsLoadUseCase {
    BaseResponse<AuthorityResponse> load(AuthoritySettingsLoadRequest request);
}
