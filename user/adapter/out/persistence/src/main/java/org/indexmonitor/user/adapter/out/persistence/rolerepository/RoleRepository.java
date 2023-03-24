package org.indexmonitor.user.adapter.out.persistence.rolerepository;

import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.indexmonitor.user.adapter.out.persistence.models.RoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM RoleEntity e WHERE e.id = :id OR e.name = :name")
    boolean existsByIdOrName(@Param("id") UUID id, @Param("name") String name);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM RoleEntity e WHERE e.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT e.id AS id, e.name AS name FROM RoleEntity e WHERE e.name IN :names")
    List<RoleProjection> findAllByNameIn(@Param("names") Set<String> names);

    @Query("SELECT e FROM RoleEntity e WHERE e.name IN :names")
    List<RoleEntity> findAllByNames(@Param("names") Set<String> names);

    @Query(value = "SELECT COUNT(*) > 0 FROM user_entity_roles WHERE roles = :roleId", nativeQuery = true)
    Boolean isUsedByAnyUser(@Param("roleId") String roleId);
}