package org.indexmonitor.auth.adapter.out.persistence.repositories;

import org.indexmonitor.auth.adapter.out.persistence.entities.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, String> {

    @Query("SELECT a FROM AuthorizationEntity a JOIN a.tokens t WHERE t= :tokenValue")
    List<AuthorizationEntity> findByTokens_Value(@Param("tokenValue") String tokenValue);

    Optional<AuthorizationEntity> findByTokenValue(String token);
}
