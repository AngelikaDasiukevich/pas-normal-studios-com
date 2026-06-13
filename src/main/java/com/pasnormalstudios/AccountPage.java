package com.pasnormalstudios;

import com.pasnormalstudios.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public AccountPage() {
        super();
    }

    public String getTitleText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
        log.info("Fetching Title Text: {}", text);
        return text;
    }

    public void setEmailInput(String email) {
        log.info("Typing email: '{}'", email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_INPUT))).sendKeys(email);
    }

    public void setPasswordInput(String password) {
        log.info("Typing password: '{}'", password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INPUT))).sendKeys(password);
    }

    public void clickSubmitLoginButton() {
        log.info("Clicking the login submit button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_LOGIN_BUTTON))).click();
    }

    public void clickSubmitSignUpButton() {
        log.info("Clicking the sign-up submit button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_SIGN_UP_BUTTON))).click();
    }

    public boolean isSubmitLoginButtonVisible() {
        boolean isVisible = driver.findElement(By.xpath(SUBMIT_LOGIN_BUTTON)).isDisplayed();
        log.info("Checking if login submit button is visible. Result: {}", isVisible);
        return isVisible;
    }

    public boolean isSubmitSignUpButtonVisible() {
        boolean isVisible = driver.findElement(By.xpath(SUBMIT_SIGN_UP_BUTTON)).isDisplayed();
        log.info("Checking if sign-up submit button is visible. Result: {}", isVisible);
        return isVisible;
    }

    public void setEmailPasswordAndClickLoginButton(User user) {
        log.info("Performing login for user: {} with password: {}", user.getEmail(), user.getPassword());
        this.setEmailInput(user.getEmail());
        this.setPasswordInput(user.getPassword());
        this.clickSubmitLoginButton();
    }

    public String getEmailAlertMessageText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALERT_MESSAGE))).getText();
        log.info("Fetched Email alert message: '{}'", text);
        return text;
    }

    public String getPasswordAlertMessageText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_ALERT_MESSAGE))).getText();
        log.info("Fetched Password alert message: '{}'", text);
        return text;
    }

    public String getInvalidEmailPasswordAlertMessageText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_PASSWORD_ALERT_MESSAGE))).getText();
        log.info("Fetched invalid credentials alert message: '{}'", text);
        return text;
    }

    public void clickForgetPasswordButton() {
        log.info("Clicking 'Forgot Password' button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FORGET_PASSWORD_BUTTON))).click();
    }

    public void clickCreateAccountButton() {
        log.info("Clicking 'Create Account' button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_ACCOUNT_BUTTON))).click();
    }

    public void clickLoginButton() {
        log.info("Clicking 'Login' button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_BUTTON))).click();
    }
}
