package com.chat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String name);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.email = ?2, u.password =?3 WHERE u.id = ?1")
    int updateUser(Long id, String email, String password);
}

