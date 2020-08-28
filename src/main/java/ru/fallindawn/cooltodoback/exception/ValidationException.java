package ru.fallindawn.cooltodoback.exception;

public class ValidationException extends Exception {
    private String message;

    public ValidationException(String message) {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
