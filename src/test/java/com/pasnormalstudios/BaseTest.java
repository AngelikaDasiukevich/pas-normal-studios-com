package com.pasnormalstudios;

import com.pasnormalstudios.driver.Driver;
import com.pasnormalstudios.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected HomePage homePage;
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
