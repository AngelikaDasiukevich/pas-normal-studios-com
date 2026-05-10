package com.pasnormalstudios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResetPasswordTest extends BaseTest {
    AccountPage accountPage;
    ResetPasswordPage resetPasswordPage;

    @BeforeEach
    public void setupAccount() {
        accountPage = new AccountPage(driver);
        homePage.clickAccountLink();
        accountPage.clickForgetPasswordButton();
        resetPasswordPage = new ResetPasswordPage(driver);
    }

    @Test
    public void assertEmailAlertMessage() {
        resetPasswordPage.clickSubmitButton();
        Assertions.assertEquals("Email is required", resetPasswordPage.getEmailAlertMessageText());
    }

    @Test
    public void assertResetPasswordInfoMessage() {
        resetPasswordPage.setRandomEmailInput();
        resetPasswordPage.clickSubmitButton();
        Assertions.assertEquals("Reset instructions have been sent to your email", resetPasswordPage.getResetPasswordInfoMessageText());
    }

    @Test
    public void assertBackToLoginButton() {
        resetPasswordPage.setRandomEmailInput();
        resetPasswordPage.clickSubmitButton();
        resetPasswordPage.clickBackToLoginButton();
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }
}
