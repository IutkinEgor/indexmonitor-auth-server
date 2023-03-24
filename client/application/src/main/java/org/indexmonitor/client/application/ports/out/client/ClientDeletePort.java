package org.indexmonitor.client.application.ports.out.client;

import org.indexmonitor.common.domain.interfaces.Port;

import java.util.UUID;

public interface ClientDeletePort extends Port {

    void delete(UUID id);
}
