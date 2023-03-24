package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateTokenSettingsRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ClientTokenSettingsUpdateUseCase extends UseCase {
    BaseResponse update(ClientUpdateTokenSettingsRequest request);
}
