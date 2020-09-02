package ru.fallindawn.cooltodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fallindawn.cooltodoback.entity.security.Role;
import ru.fallindawn.cooltodoback.entity.security.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
