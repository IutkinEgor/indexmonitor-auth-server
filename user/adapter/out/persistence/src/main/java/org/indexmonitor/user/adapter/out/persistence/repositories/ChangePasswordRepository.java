package org.indexmonitor.user.adapter.out.persistence.repositories;

import org.indexmonitor.user.adapter.out.persistence.entities.ChangePasswordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ChangePasswordRepository extends CrudRepository<ChangePasswordEntity, UUID> {
    @Query("SELECT e FROM  ChangePasswordEntity e WHERE e.id = :id")
    Optional<ChangePasswordEntity> findById(@Param("id") UUID id);
    @Query("SELECT e FROM  ChangePasswordEntity e WHERE e.user.userId = :userId")
    Optional<ChangePasswordEntity> findByUserId(@Param("userId") UUID userId);
    @Query("SELECT e FROM  ChangePasswordEntity e WHERE e.tokenHash = :token")
    Optional<ChangePasswordEntity> findByToken(@Param("token") String token);


}
