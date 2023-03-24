package org.indexmonitor.configuration.application.configs;
import org.indexmonitor.common.adapter.in.api.config.CommonApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CommonApiConfig.class
})
public class CommonConfig {
}
