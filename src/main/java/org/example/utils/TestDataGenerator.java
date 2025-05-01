package org.example.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public final class TestDataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    private TestDataGenerator() { }

    public static String randomLogin() {
        return "auto_" + faker.name().username() + System.currentTimeMillis();
    }

    public static String randomPassword() {
        return faker.internet().password(8, 16);
    }

    public static String randomName() {
        return faker.name().firstName();
    }
}