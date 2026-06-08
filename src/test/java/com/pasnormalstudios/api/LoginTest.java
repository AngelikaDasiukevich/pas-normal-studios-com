package com.pasnormalstudios.api;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {
    @Test
    public void testLogin() {
        final String API_HOME = "https://pasnormalstudios.com/api/";
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"" + "test@test.com" + "\"," +
                "\"current-password\":\"" + "123123123" + "\"}";
        given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                        "Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body).when().post(LOGIN_URL)
                .then().log().all();
    }

    @Test
    public void testLogin2() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 20);

        LoginClient loginClient = new LoginClient();
        loginClient.doRequest(email, password);

        Assertions.assertEquals(403, loginClient.getStatusCode());
    }

    @Test
    public void testLogin3() {
        User user = Users.getRandomUser();

        LoginClient loginClient = new LoginClient();
        loginClient.doRequest2(user);

        Assertions.assertEquals(403, loginClient.getStatusCode());
    }
}
