package ru.fallindawn.cooltodoback.utils;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fallindawn.cooltodoback.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateTest {
    private static final List<String> validEmailData = new ArrayList<>();
    private static final List<String> invalidEmailData = new ArrayList<>();

    @BeforeEach
    void setUp() {
        validEmailData.add("pussy.killa@acidhouze.ru");
        validEmailData.add("abc123@en.en");
        validEmailData.add("абв123@ру.ру");
        validEmailData.add("абtz@.ру");

        invalidEmailData.add("_da@.xyz");
        invalidEmailData.add("2+2.ru");
        invalidEmailData.add(".@_@.");
    }

    @Test
    void validMailValidate() throws ValidationException {
        for (String mail : validEmailData)
            assertTrue(Validate.mail(mail));
    }

    @Test
    void invalidMailValidate() throws ValidationException {
        for (String mail : invalidEmailData)
            assertFalse(Validate.mail(mail));
    }
}
