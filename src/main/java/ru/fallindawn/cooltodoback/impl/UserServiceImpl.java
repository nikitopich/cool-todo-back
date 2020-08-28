package ru.fallindawn.cooltodoback.impl;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.entity.User;
import ru.fallindawn.cooltodoback.exception.ValidationException;
import ru.fallindawn.cooltodoback.repository.UserRepository;
import ru.fallindawn.cooltodoback.service.UserService;
import ru.fallindawn.cooltodoback.utils.UserConverter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Log
public class UserServiceImpl implements UserService {
    private static final String EMAIL_PATTERN = "^[A-Za-zА-Яа-я\\d.-]+@[A-Za-zА-Яа-я\\d.-]+$";

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
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
        if (isNull(user))
            return userConverter.fromUserToUserDto(user);

        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validateMail(String email) throws ValidationException {
        if (email.isEmpty()) {
            throw new ValidationException("EMail is empty");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    public void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }

        if (!validateMail(userDto.getEmail())) {
            throw new ValidationException("EMail is invalid");
        }

        if (userDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    public boolean isNull(Object obj) {
        return obj == null;
    }
}
