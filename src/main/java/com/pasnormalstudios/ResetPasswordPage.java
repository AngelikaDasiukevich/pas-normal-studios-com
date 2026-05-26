package com.pasnormalstudios;

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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
    }

    public void setEmailInput(String email) {
        //String email = faker.internet().emailAddress();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_INPUT))).sendKeys(email);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_BUTTON))).click();
    }

    public void setEmailAndClickSubmitButton(User user) {
        this.setEmailInput(user.getEmail());
        this.clickSubmitButton();
    }

    public String getEmailAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALERT_MESSAGE))).getText();
    }

    public String getResetPasswordInfoMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESET_PASSWORD_INFO_MESSAGE))).getText();
    }

    public void clickBackToLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACK_TO_LOGIN_BUTTON))).click();
    }
}
