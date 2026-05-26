package com.pasnormalstudios.data;

import net.datafaker.Faker;

public class UserFactory {
    private static final Faker FAKER = new Faker();

    public static User createValidUser() {
        String generatedEmail = FAKER.internet().emailAddress();
        String generatedPassword = FAKER.internet().password(8, 20);
        return new User(generatedEmail, generatedPassword);
    }
}
