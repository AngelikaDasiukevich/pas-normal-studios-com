package com.pasnormalstudios.data;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Users {
    private static final Faker FAKER = new Faker();
    private static final Logger log = LogManager.getLogger();

    public static User getRandomUser() {
        String generatedEmail = FAKER.internet().emailAddress();
        String generatedPassword = FAKER.internet().password(8, 20);
        log.info("Generated random user with email {}, password {}", generatedEmail, generatedPassword);
        return new User(generatedEmail, generatedPassword);
    }
}
