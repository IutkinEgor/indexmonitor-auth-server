package org.indexmonitor.auth.application.ports.in;

import org.indexmonitor.auth.application.ports.in.requests.AuthorizationLoadByIdRequest;
import org.indexmonitor.auth.application.ports.in.requests.AuthorizationLoadByTokenRequest;
import org.indexmonitor.auth.application.models.Authorization;
import org.indexmonitor.common.domain.interfaces.UseCase;

public interface AuthorizationLoadUseCase extends UseCase {
    Authorization loadById(AuthorizationLoadByIdRequest request);
    Authorization loadByToken(AuthorizationLoadByTokenRequest request);
}
