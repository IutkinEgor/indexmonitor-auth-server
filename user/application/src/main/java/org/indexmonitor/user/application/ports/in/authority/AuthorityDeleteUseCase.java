package org.indexmonitor.user.application.ports.in.authority;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityDeleteRequest;

public interface AuthorityDeleteUseCase extends UseCase {
    BaseResponse delete(AuthorityDeleteRequest request);
}
