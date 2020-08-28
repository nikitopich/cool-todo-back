package ru.fallindawn.cooltodoback.impl;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.exception.ValidationException;
import ru.fallindawn.cooltodoback.repository.UserRepository;
import ru.fallindawn.cooltodoback.service.UserService;
import ru.fallindawn.cooltodoback.utils.UserConverter;
import ru.fallindawn.cooltodoback.utils.Validate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        Validate.userDto(userDto);
        User savedUser = userRepository.save(userConverter.fromUserDtoToUser(userDto));

        return userConverter.fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return Validate.isNull(user) ? null : userConverter.fromUserToUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}
