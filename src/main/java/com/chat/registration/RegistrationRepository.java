package com.chat.registration;


import com.chat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
