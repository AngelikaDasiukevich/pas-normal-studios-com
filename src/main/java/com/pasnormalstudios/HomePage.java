package com.pasnormalstudios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private final String URL = "https://pasnormalstudios.com/";
    private final String ALLOW_COOKIES_BUTTON = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";
    private final String ACCOUNT_LINK = "//span[contains(text(), 'Account')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void open() {
        driver.get(URL);
    }

    public void clickAllowCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ALLOW_COOKIES_BUTTON))).click();
    }

    public void clickAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_LINK))).click();
    }


}
