package org.indexmonitor.configuration.application.configs;

import org.indexmonitor.user.adapter.in.mvc.configs.UserMvcConfig;
import org.indexmonitor.user.adapter.in.api.configs.UserApiConfig;
import org.indexmonitor.user.adapter.out.email.config.UserEmailConfig;
import org.indexmonitor.user.adapter.out.persistence.configs.UserPersistenceConfig;
import org.indexmonitor.user.application.configs.UserAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        UserApiConfig.class,
        UserMvcConfig.class,
        UserAppConfig.class,
        UserEmailConfig.class,
        UserPersistenceConfig.class,
})
public class UserConfig {
}
