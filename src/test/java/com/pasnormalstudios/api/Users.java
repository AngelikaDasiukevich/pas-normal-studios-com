package com.pasnormalstudios.api;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Users {
    private static final Logger log = LogManager.getLogger();

    public static User getRandomUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 20);
        log.info("Generated random user with email {} password {}", email, password);
        return new User(email, password);
    }
}
