package org.indexmonitor.configuration.application.configs;

import org.indexmonitor.client.adapter.in.api.configs.ClientApiConfig;
import org.indexmonitor.client.adapter.out.persistence.entities.configs.ClientPersistenceConfig;
import org.indexmonitor.client.application.configs.ClientAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ClientApiConfig.class,
        ClientAppConfig.class,
        ClientPersistenceConfig.class
})
public class ClientConfig {
}
