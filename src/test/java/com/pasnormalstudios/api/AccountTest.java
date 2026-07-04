package com.pasnormalstudios.api;

import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    User user;
    ApiService apiService;

    @BeforeEach
    public void setupApi() {
        user = Users.getRandomUser();
        apiService = new ApiService();
    }

    @Test
    public void invalidLogin() {
        final String body = "{" +
                "\"email\":\"" + user.getEmail() + "\"," +
                "\"current-password\":\"" + user.getPassword() + "\"}";

        apiService.doPostRequest(body);
        Assertions.assertEquals(403, apiService.getStatusCode());
        Assertions.assertEquals("Invalid email or password", apiService.getMessageFromResponse());
    }

    @Test
    public void blankEmail() {
        final String body = "{" +
                "\"email\":\"\"," +
                "\"current-password\":\"" + user.getPassword() + "\"}";

        apiService.doPostRequest(body);
        Assertions.assertEquals(403, apiService.getStatusCode());
        Assertions.assertEquals("", apiService.getMessageFromResponse());
    }

    @Test
    public void blankPassword() {
        final String body = "{" +
                "\"email\":\"" + user.getEmail() + "\"," +
                "\"current-password\":\"\"}";

        apiService.doPostRequest(body);
        Assertions.assertEquals(403, apiService.getStatusCode());
        Assertions.assertEquals("", apiService.getMessageFromResponse());
    }

    @Test
    public void blankBody() {
        final String body = "{}";

        apiService.doPostRequest(body);
        Assertions.assertEquals(403, apiService.getStatusCode());
        Assertions.assertEquals("", apiService.getMessageFromResponse());
    }
}
