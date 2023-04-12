package org.indexmonitor.client.adapter.out.persistence.repositories;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ScopeRepository extends JpaRepository<ScopeEntity, UUID> {

    Set<ScopeEntity> findAllById(Set<UUID> uuids);
    @Query("SELECT e FROM ScopeEntity e WHERE e.name = :name")
    Optional<ScopeEntity> findByName(@Param("name")String name);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM ScopeEntity e WHERE e.id = :id OR e.name = :name")
    boolean existsByIdOrName(@Param("id") UUID id, @Param("name") String name);
    @Query("SELECT CASE WHEN COUNT(e) > 1 THEN 'true' ELSE 'false' END FROM ScopeEntity e WHERE e.id = :id OR e.name = :name")
    boolean existsMoreThenOneByIdOrName(@Param("id") UUID id, @Param("name") String name);
}
