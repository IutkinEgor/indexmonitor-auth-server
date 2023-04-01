package org.indexmonitor.user.adapter.in.api.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@ComponentScan("org.indexmonitor.user.adapter.in.api")
public class UserApiConfig {


}
