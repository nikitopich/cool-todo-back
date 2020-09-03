package ru.fallindawn.cooltodoback.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.entity.security.Role;
import ru.fallindawn.cooltodoback.entity.security.RoleName;
import ru.fallindawn.cooltodoback.exception.UserRegistretionException;
import ru.fallindawn.cooltodoback.repository.RoleRepository;
import ru.fallindawn.cooltodoback.repository.UserRepository;
import ru.fallindawn.cooltodoback.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Log
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(String login, String password, String email, Set<String> rolesStrings) {
        String trimmedLoginInLowerCase = login.trim().toLowerCase();

        validateLogin(trimmedLoginInLowerCase);
        validateMail(email);

        User user = new User(trimmedLoginInLowerCase, passwordEncoder.encode(password), email);
        user.setRoles(validateAndGetRegisteredRoles(rolesStrings));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User findByLogin(String login) {
        return userRepository.findByLoginIgnoreCase(login.trim())
                .orElseThrow(() -> new EntityNotFoundException("User not found with -> login: " + login));
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    public void validateLogin(String login) {
        if (userRepository.existsByLogin(login))
            throw new UserRegistretionException("User with this login already exist!");
    }

    public void validateMail(String email) {
        if (userRepository.existsByEmail(email))
            throw new UserRegistretionException("User with this email already exist!");
    }

    private Set<Role> validateAndGetRegisteredRoles(Set<String> rolesStrings) {
        Set<Role> registeredRoles = new HashSet<>();

        rolesStrings.forEach(roleString -> {
            RoleName registeredRoleName = extractRoleNameFromRoleString(roleString);
            Role registeredRole = roleRepository.findByName(registeredRoleName)
                    .orElseThrow(() -> new UserRegistretionException("Could not find provided role by role name in the database"));
            registeredRoles.add(registeredRole);
        });

        return registeredRoles;
    }

    private RoleName extractRoleNameFromRoleString(String roleString) {
        switch (roleString.trim().toLowerCase()) {
            case "admin":
                return RoleName.ROLE_ADMIN;
            case "user":
                return RoleName.ROLE_USER;
            default:
                throw new UserRegistretionException("Invalid role was given for registration");
        }
    }
}
