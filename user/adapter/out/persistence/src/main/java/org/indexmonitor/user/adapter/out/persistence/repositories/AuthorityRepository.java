package org.indexmonitor.user.adapter.out.persistence.repositories;

import org.indexmonitor.user.adapter.out.persistence.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {

    @Query("SELECT e FROM AuthorityEntity e WHERE e.name IN :names")
    List<AuthorityEntity> findAllByNames(@Param("names") Set<String> names);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM AuthorityEntity e WHERE e.id = :id OR e.name = :name")
    boolean existsByIdOrName(@Param("id") UUID id, @Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM AuthorityEntity e WHERE e.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query(value = "SELECT COUNT(*) > 0 FROM user_entity_authorities WHERE authorities = :authorityId", nativeQuery = true)
    Boolean isUsedByAnyUser(@Param("authorityId") String authorityId);
}
