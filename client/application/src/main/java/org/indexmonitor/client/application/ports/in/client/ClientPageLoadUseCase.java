package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadPageRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientPageModelResponse;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

import java.util.Set;

public interface ClientPageLoadUseCase extends UseCase {
    BasePageResponse<ClientPageModelResponse> load(ClientLoadPageRequest request);
}
