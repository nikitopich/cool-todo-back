package ru.fallindawn.cooltodoback.utils;

import org.junit.jupiter.api.BeforeEach;

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

}
