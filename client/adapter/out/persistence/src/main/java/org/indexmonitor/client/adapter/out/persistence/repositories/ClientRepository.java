package org.indexmonitor.client.adapter.out.persistence.repositories;

import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

//    @Query(value = "SELECT client FROM ClientEntity client OFFSET :offset LIMIT :limit", nativeQuery = true)
//    Set<ClientEntity> findAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
    @Query("SELECT client FROM ClientEntity client WHERE client.clientId = :clientId")
    Optional<ClientEntity> findByClientId(@Param("clientId")String clientId);
    @Query("SELECT client FROM ClientEntity client WHERE client.name = :name")
    Optional<ClientEntity> findByClientName(@Param("name")String name);
    @Query("SELECT client FROM ClientEntity client WHERE client.origin = :origin")
    Optional<ClientEntity> findByOrigin(@Param("origin")String origin);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM ClientEntity e WHERE e.id = :id OR e.clientId = :clientId OR e.name = :name OR e.origin = :origin")
    boolean existsByIdOrClientIdOrClientNameOrOrigin(@Param("id") UUID id, @Param("clientId") String clientId, @Param("name") String name, @Param("origin") String origin);

    @Query("SELECT CASE WHEN COUNT(e) > 1 THEN 'true' ELSE 'false' END FROM ClientEntity e WHERE e.id = :id OR e.clientId = :clientId OR e.name = :name OR e.origin = :origin")
    boolean existsMoreThenOneByIdOrClientIdOrClientNameOrOrigin(@Param("id") UUID id, @Param("clientId") String clientId, @Param("name") String name, @Param("origin") String origin);

    @Query("SELECT c FROM ClientEntity c JOIN c.scopes s WHERE s.id = :scopeId")
    Set<ClientEntity> findAllByScopeId(@Param("scopeId") UUID scopeId);

}
