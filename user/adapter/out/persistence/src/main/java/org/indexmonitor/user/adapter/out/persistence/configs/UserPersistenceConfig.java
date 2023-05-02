package org.indexmonitor.user.adapter.out.persistence.configs;

import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.repositories.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan("org.indexmonitor.user.adapter.out.persistence")
@EntityScan(basePackageClasses = {
        AuthorityRepository.class,
        ConfirmEmailRepository.class,
        ResetPasswordRepository.class,
        ChangePasswordRepository.class,
        UserRepository.class,
        UserEntity.class
})
@EnableJpaRepositories("org.indexmonitor.user.adapter.out.persistence.repositories")
@Import(RolePersistenceConfig.class)
public class UserPersistenceConfig {


}
