package com.pasnormalstudios.data;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Users {
    private static final Faker FAKER = new Faker();
    private static final Logger log = LogManager.getLogger();
    private static final int MIN_PASSWORD_LENGTH = 1;
    private static final int MAX_PASSWORD_LENGTH = 25;

    public static User getRandomUser() {
        String generatedEmail = FAKER.internet().emailAddress();
        String generatedPassword = FAKER.internet().password(MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
        log.info("Generated random user with email {}, password {}", generatedEmail, generatedPassword);
        return new User(generatedEmail, generatedPassword);
    }
}
