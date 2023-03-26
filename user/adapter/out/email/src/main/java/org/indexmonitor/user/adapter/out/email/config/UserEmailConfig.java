package org.indexmonitor.user.adapter.out.email.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan("org.indexmonitor.user.adapter.out.email")
public class UserEmailConfig {
}
