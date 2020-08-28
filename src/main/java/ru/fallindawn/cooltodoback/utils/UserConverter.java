package ru.fallindawn.cooltodoback.utils;

import org.springframework.stereotype.Component;
import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.entity.User;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLogin(),
                userDto.getEmail()
        );
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}
