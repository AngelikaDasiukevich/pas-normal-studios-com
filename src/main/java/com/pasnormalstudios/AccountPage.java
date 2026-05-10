package com.pasnormalstudios;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage {
    private Faker faker;
    private final String TITLE = "//h2[contains(@class, 'text-black')]";
    private final String EMAIL_INPUT = "//input[@type='email']";
    private final String PASSWORD_INPUT = "//input[@type='password']";
    private final String LOGIN_BUTTON = "//button[@type='submit']";
    private final String EMAIL_ALERT_MESSAGE = "//p[contains(text(), 'Email is required')]";
    private final String PASSWORD_ALERT_MESSAGE = "//p[contains(text(), 'Password is required')]";
    private final String INVALID_EMAIL_PASSWORD_ALERT_MESSAGE = "//p[contains(text(), 'Invalid email or password')]";

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String getTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
    }

    public void setRandomEmailInput() {
        faker = new Faker();
        String email = faker.internet().emailAddress();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_INPUT))).sendKeys(email);
    }

    public void setRandomPasswordInput() {
        faker = new Faker();
        String password = faker.internet().password(8, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INPUT))).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_BUTTON))).click();
    }

    public void setRandomEmailPasswordAndClickLoginButton() {
        this.setRandomEmailInput();
        this.setRandomPasswordInput();
        this.clickLoginButton();
    }

    public String getEmailAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALERT_MESSAGE))).getText();
    }

    public String getPasswordAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_ALERT_MESSAGE))).getText();
    }

    public String getInvalidEmailPasswordAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_PASSWORD_ALERT_MESSAGE))).getText();
    }
}
