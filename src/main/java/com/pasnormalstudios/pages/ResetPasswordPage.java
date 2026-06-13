package com.pasnormalstudios.pages;

import com.pasnormalstudios.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResetPasswordPage extends BasePage {
    private final String TITLE = "//p[@class='text-2xl']";
    private final String EMAIL_INPUT = "//input[@type='email']";
    private final String SUBMIT_BUTTON = "//button[@type='submit']";
    private final String EMAIL_ALERT_MESSAGE = "//p[contains(text(), 'Email is required')]";
    private final String RESET_PASSWORD_INFO_MESSAGE = "//p[contains(text(), 'Reset instructions have been sent')]";
    private final String BACK_TO_LOGIN_BUTTON = "//button[contains(text(), 'Back to login')]";

    public ResetPasswordPage () {
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

    public void clickSubmitButton() {
        log.info("Clicking 'Submit' button");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_BUTTON))).click();
    }

    public void setEmailAndClickSubmitButton(User user) {
        log.info("Submitting reset password form with email: {}", user.getEmail());
        this.setEmailInput(user.getEmail());
        this.clickSubmitButton();
    }

    public String getEmailAlertMessageText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALERT_MESSAGE))).getText();
        log.info("Fetched Email alert message: '{}'", text);
        return text;
    }

    public String getResetPasswordInfoMessageText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESET_PASSWORD_INFO_MESSAGE))).getText();
        log.info("Fetched Reset Password Info message: '{}'", text);
        return text;
    }

    public void clickBackToLoginButton() {
        log.info("Clicking 'Back To Login' button");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACK_TO_LOGIN_BUTTON))).click();
    }
}
