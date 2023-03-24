package org.indexmonitor.user.adapter.out.persistence.repositories;

import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {


    @Query("SELECT e FROM UserEntity e WHERE e.userName = :userName")
    Optional<UserEntity> findByUserName(@Param("userName")String userName);

    @Query("SELECT e FROM UserEntity e INNER JOIN e.profile p WHERE p.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM UserEntity e WHERE e.userId = :id OR e.userName = :username OR e.profile.email = :email")
    boolean existsByIdOrUsernameOrEmail(@Param("id") UUID id, @Param("username") String username, @Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(e) > 1 THEN 'true' ELSE 'false' END FROM UserEntity e WHERE e.userId = :id OR e.userName = :username OR e.profile.email = :email")
    boolean existsByIdOrUsernameOrEmailMoreThanOne(@Param("id") UUID id, @Param("username") String username, @Param("email") String email);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM UserEntity e WHERE e.profile.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM UserEntity u WHERE u.userId = :userId")
    void deleteByUserIdAndCascadeAll(@Param("userId") UUID userId);
}
