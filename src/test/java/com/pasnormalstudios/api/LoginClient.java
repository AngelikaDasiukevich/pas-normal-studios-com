package com.pasnormalstudios.api;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class LoginClient {
    Response response;
    private static final Logger log = LogManager.getLogger();
    public void doRequest(String email, String password) {
        final String API_HOME = "https://pasnormalstudios.com/api/";
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"" + email + "\"," +
                "\"current-password\":\"" + password + "\"}";

        response = given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                        "Gecko) Chrome/147.0.0.0 Safari/537.36")
                .body(body).when().post(LOGIN_URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public void doRequest2(User user) {
        final String API_HOME = "https://pasnormalstudios.com/api/";
        final String LOGIN_URL = API_HOME + "auth/login";
        final String body = "{" +
                "\"email\":\"" + user.getEmail() + "\"," +
                "\"current-password\":\"" + user.getPassword() + "\"}";

        StringWriter requestWriter = new StringWriter();
        PrintStream requestPrintStream = new PrintStream(new WriterOutputStream(requestWriter, "UTF-8"), true);

        StringWriter responseWriter = new StringWriter();
        PrintStream responsePrintStream = new PrintStream(new WriterOutputStream(responseWriter, "UTF-8"), true);
        //log request
        //log response
        response = given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                        "Gecko) Chrome/147.0.0.0 Safari/537.36")
                .filter(new RequestLoggingFilter(LogDetail.ALL, requestPrintStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, responsePrintStream))
                .body(body).when().post(LOGIN_URL);

        log.info("API Request Details:\n" + requestWriter.toString());
        log.info("API Response Details:\n" + responseWriter.toString());
    }
}
