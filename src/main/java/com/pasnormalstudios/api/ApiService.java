package com.pasnormalstudios.api;

import com.pasnormalstudios.pages.BasePage;
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

public class ApiService {
    Response response;
    private static final Logger log = LogManager.getLogger();
    private final String API_HOME = BasePage.BASE_URL + "/api/";
    private final String LOGIN_API_URL = API_HOME + "auth/login";


    public void doPostRequest(String body) {
        StringWriter requestWriter = new StringWriter();
        PrintStream requestPrintStream = new PrintStream(new WriterOutputStream(requestWriter, "UTF-8"), true);

        StringWriter responseWriter = new StringWriter();
        PrintStream responsePrintStream = new PrintStream(new WriterOutputStream(responseWriter, "UTF-8"), true);

        response = given()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                "Gecko) Chrome/147.0.0.0 Safari/537.36")
                .filter(new RequestLoggingFilter(LogDetail.ALL, requestPrintStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, responsePrintStream))
                .body(body).when().post(LOGIN_API_URL);

        log.info("API Request Details:\n{}", requestWriter);
        log.info("API Response Details:\n{}", responseWriter);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        return response.jsonPath().getString("message");
    }
}
