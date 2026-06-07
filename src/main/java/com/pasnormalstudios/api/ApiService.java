package com.pasnormalstudios.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiService {
    Response response;
    private final String API_HOME = "https://pasnormalstudios.com/api/";
    private final String LOGIN_URL = API_HOME + "auth/login";

    public void doPostRequest(String body) {
        response = given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                "Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body).when().post(LOGIN_URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        return response.jsonPath().getString("message");
    }
}
