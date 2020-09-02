package ru.fallindawn.cooltodoback.service;

import ru.fallindawn.cooltodoback.entity.User;

import java.util.Set;

public interface UserService {
    void createUser(String login, String password, String email, Set<String> rolesStrings);

    void deleteUser(Long userId);

    User findByLogin(String login);

    User save(User user);

}
