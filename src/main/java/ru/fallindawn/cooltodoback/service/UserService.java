package ru.fallindawn.cooltodoback.service;

import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.exception.ValidationException;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(Long userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
}
