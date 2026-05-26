package com.pasnormalstudios;

import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResetPasswordTest extends BaseTest {
    AccountPage accountPage;
    ResetPasswordPage resetPasswordPage;
    User user;

    @BeforeEach
    public void setupAccount() {
        accountPage = new AccountPage();
        homePage.clickAccountLink();
        accountPage.clickForgetPasswordButton();
        resetPasswordPage = new ResetPasswordPage();
        user = UserFactory.createValidUser();
    }

    @Test
    public void assertEmailAlertMessage() {
        resetPasswordPage.clickSubmitButton();
        Assertions.assertEquals("Email is required", resetPasswordPage.getEmailAlertMessageText());
    }

    @Test
    public void assertResetPasswordInfoMessage() {
        resetPasswordPage.setEmailAndClickSubmitButton(user);
        Assertions.assertEquals("Reset instructions have been sent to your email", resetPasswordPage.getResetPasswordInfoMessageText());
    }

    @Test
    public void assertBackToLoginButton() {
        resetPasswordPage.setEmailAndClickSubmitButton(user);
        resetPasswordPage.clickBackToLoginButton();
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }
}
