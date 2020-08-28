package ru.fallindawn.cooltodoback.utils;

import org.springframework.stereotype.Component;
import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.entity.User;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getSurname()
        );
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
