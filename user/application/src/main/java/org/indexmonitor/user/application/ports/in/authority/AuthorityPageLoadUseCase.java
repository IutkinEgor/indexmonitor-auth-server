package org.indexmonitor.user.application.ports.in.authority;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityPageLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityPageResponse;

public interface AuthorityPageLoadUseCase extends UseCase {
    BasePageResponse<AuthorityPageResponse> load(AuthorityPageLoadRequest request);
}
