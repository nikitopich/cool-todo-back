package ru.fallindawn.cooltodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fallindawn.cooltodoback.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginIgnoreCase(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
