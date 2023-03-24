package org.indexmonitor.client.application.ports.in.client;


import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ClientRegisterUseCase extends UseCase {

    BaseResponse register(ClientRegisterRequest command);
}
