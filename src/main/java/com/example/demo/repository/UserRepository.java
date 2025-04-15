package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM UserEntity u " +
                    "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByUsername(String username);
}
