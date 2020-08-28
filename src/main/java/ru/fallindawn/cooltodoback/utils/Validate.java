package ru.fallindawn.cooltodoback.utils;

import ru.fallindawn.cooltodoback.dto.UserDto;
import ru.fallindawn.cooltodoback.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final String EMAIL_PATTERN = "^[A-Za-zА-Яа-я\\d.-]+@[A-Za-zА-Яа-я\\d.-]+$";

    private Validate() {
    }

    public static boolean mail(String email) throws ValidationException {
        if (email.isEmpty()) {
            throw new ValidationException("EMail is empty");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    public static void userDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }

        if (!Validate.mail(userDto.getEmail())) {
            throw new ValidationException("EMail is invalid");
        }

        if (userDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
