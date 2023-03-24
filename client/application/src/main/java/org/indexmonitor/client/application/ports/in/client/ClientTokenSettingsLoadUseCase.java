package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientTokenSettingsResponse;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ClientTokenSettingsLoadUseCase extends UseCase {
    BaseResponse<ClientTokenSettingsResponse> findById(ClientLoadRequest request);
}
