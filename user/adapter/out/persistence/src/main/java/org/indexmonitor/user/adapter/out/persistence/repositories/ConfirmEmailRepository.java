package org.indexmonitor.user.adapter.out.persistence.repositories;

import org.indexmonitor.user.adapter.out.persistence.entities.ConfirmEmailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmEmailRepository extends CrudRepository<ConfirmEmailEntity, UUID> {

    @Query("SELECT e FROM  ConfirmEmailEntity e WHERE e.id = :id")
    Optional<ConfirmEmailEntity> findById(@Param("id") UUID id);
    @Query("SELECT e FROM  ConfirmEmailEntity e WHERE e.user.userId = :userId")
    Optional<ConfirmEmailEntity> findByUserId(@Param("userId") UUID userId);
    @Query("SELECT e FROM  ConfirmEmailEntity e WHERE e.tokenHash = :token")
    Optional<ConfirmEmailEntity> findByToken(@Param("token") String token);
    void deleteByUserUserId(@Param("userId") UUID userId);
}
