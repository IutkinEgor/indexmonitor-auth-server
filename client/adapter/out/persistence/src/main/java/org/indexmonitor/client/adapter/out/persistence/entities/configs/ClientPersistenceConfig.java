package org.indexmonitor.client.adapter.out.persistence.entities.configs;
import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.repositories.ClientRepository;
import org.indexmonitor.client.adapter.out.persistence.repositories.ScopeRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@ComponentScan("org.indexmonitor.client.adapter.out.persistence")
@EntityScan(basePackageClasses = {
        ClientRepository.class,
        ClientEntity.class,
        ScopeRepository.class,
        ScopeEntity.class
})
@EnableJpaRepositories("org.indexmonitor.client.adapter.out.persistence")
public class ClientPersistenceConfig {
}
