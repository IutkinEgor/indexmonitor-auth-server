package org.indexmonitor.user.adapter.out.persistence.repositories;

import org.indexmonitor.user.adapter.out.persistence.entities.ResetPasswordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ResetPasswordRepository extends CrudRepository<ResetPasswordEntity, UUID> {

    @Query("SELECT e FROM  ResetPasswordEntity e WHERE e.id = :id")
    Optional<ResetPasswordEntity> findById(@Param("id") UUID id);
    @Query("SELECT e FROM  ResetPasswordEntity e WHERE e.user.userId = :userId")
    Optional<ResetPasswordEntity> findByUserId(@Param("userId") UUID userId);
    @Query("SELECT e FROM  ResetPasswordEntity e WHERE e.tokenHash = :token")
    Optional<ResetPasswordEntity> findByToken(@Param("token") String token);
    void deleteByUserUserId(@Param("userId") UUID userId);
}
