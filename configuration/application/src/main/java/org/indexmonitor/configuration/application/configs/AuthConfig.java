package org.indexmonitor.configuration.application.configs;

import org.indexmonitor.auth.application.configs.AuthAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AuthAppConfig.class
})
public class AuthConfig {
}
