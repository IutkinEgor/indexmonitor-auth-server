package org.indexmonitor.user.adapter.out.persistence.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EntityScan(basePackageClasses = { RoleRepository.class })
@EnableJpaRepositories("org.indexmonitor.user.adapter.out.persistence.rolerepository")
public class RolePersistenceConfig {
}
