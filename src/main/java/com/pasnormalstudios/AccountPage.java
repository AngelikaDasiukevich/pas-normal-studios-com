package com.pasnormalstudios;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage {
    private final String TITLE = "//h2[contains(@class, 'text-black')]";
    private final String EMAIL_INPUT = "//input[@type='email']";
    private final String PASSWORD_INPUT = "//input[@type='password']";
    private final String SUBMIT_LOGIN_BUTTON = "//span[contains(text(), 'Login')]";
    private final String SUBMIT_SIGN_UP_BUTTON = "//span[contains(text(), 'Sign up')]";
    private final String EMAIL_ALERT_MESSAGE = "//p[contains(text(), 'Email is required')]";
    private final String PASSWORD_ALERT_MESSAGE = "//p[contains(text(), 'Password is required')]";
    private final String INVALID_EMAIL_PASSWORD_ALERT_MESSAGE = "//p[contains(text(), 'Invalid email or password')]";
    private final String FORGET_PASSWORD_BUTTON = "//button[contains(text(), 'Forget password')]";
    private final String CREATE_ACCOUNT_BUTTON = "//span[contains(text(), 'Create your account')]";
    private final String LOGIN_BUTTON = "//span[contains(text(), 'Already have an account? Login')]";

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

    public void clickSubmitLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_LOGIN_BUTTON))).click();
    }

    public void clickSubmitSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_SIGN_UP_BUTTON))).click();
    }

    public boolean isSubmitLoginButtonVisible() {
        return driver.findElement(By.xpath(SUBMIT_LOGIN_BUTTON)).isDisplayed();
    }

    public boolean isSubmitSignUpButtonVisible() {
        return driver.findElement(By.xpath(SUBMIT_SIGN_UP_BUTTON)).isDisplayed();
    }

    public void setRandomEmailPasswordAndClickLoginButton() {
        this.setRandomEmailInput();
        this.setRandomPasswordInput();
        this.clickSubmitLoginButton();
    }

    public String getEmailAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALERT_MESSAGE))).getText();
    }

    public String getPasswordAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_ALERT_MESSAGE))).getText();
    }

    public String getInvalidEmailPasswordAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_PASSWORD_ALERT_MESSAGE))).getText();
    }

    public void clickForgetPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FORGET_PASSWORD_BUTTON))).click();
    }

    public void clickCreateAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_ACCOUNT_BUTTON))).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_BUTTON))).click();
    }
}
