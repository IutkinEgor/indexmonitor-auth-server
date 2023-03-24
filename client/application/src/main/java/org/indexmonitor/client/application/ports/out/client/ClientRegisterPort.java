package org.indexmonitor.client.application.ports.out.client;

import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.domain.interfaces.Port;

public interface ClientRegisterPort extends Port {

    void register(Client client);
}
