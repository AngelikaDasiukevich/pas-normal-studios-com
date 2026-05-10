package com.pasnormalstudios.api;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AccountTest {
    private final String API_HOME = "https://pasnormalstudios.com/api/";

    @Test
    public void invalidLogin() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(16, 20);
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"" + email + "\"," +
                "\"current-password\":\"" + password + "\"}";

        given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body)
        .when()
                .post(LOGIN_URL)
        .then()
                .log().all()
                .statusCode(403)
                .body("message", equalTo("Invalid email or password"));
    }

    @Test
    public void blankEmail() {
        Faker faker = new Faker();
        String password = faker.internet().password(16, 20);
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"\"," +
                "\"current-password\":\"" + password + "\"}";

        given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body)
        .when()
                .post(LOGIN_URL)
        .then()
                .log().all()
                .statusCode(403)
                .body("message", equalTo(""));
    }

    @Test
    public void blankPassword() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"" + email + "\"," +
                "\"current-password\":\"\"}";

        given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body)
                .when()
                .post(LOGIN_URL)
                .then()
                .log().all()
                .statusCode(403)
                .body("message", equalTo(""));
    }

    @Test
    public void blankBody() {
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{}";
        System.out.println(body);

        given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body)
                .when()
                .post(LOGIN_URL)
                .then()
                .log().all()
                .statusCode(403)
                .body("message", equalTo(""));
    }
}
