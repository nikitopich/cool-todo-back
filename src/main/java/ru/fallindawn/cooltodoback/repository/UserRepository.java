package ru.fallindawn.cooltodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fallindawn.cooltodoback.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
