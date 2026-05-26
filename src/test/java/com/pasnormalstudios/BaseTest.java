package com.pasnormalstudios;

import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    HomePage homePage;
    WebDriver driver;


    @BeforeEach
    public void beforeEach() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        homePage = new HomePage();
        homePage.open();
        homePage.clickAllowCookiesButton();
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) {
            Driver.quitDriver();
        }
    }
}
