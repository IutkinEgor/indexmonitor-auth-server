package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientDeleteRequest;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ClientDeleteUseCase {
    BaseResponse deleteById(ClientDeleteRequest command);
}
