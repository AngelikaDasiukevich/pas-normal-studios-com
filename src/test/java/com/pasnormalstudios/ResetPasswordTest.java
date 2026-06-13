package com.pasnormalstudios;

import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResetPasswordTest extends BaseTest {
    AccountPage accountPage;
    ResetPasswordPage resetPasswordPage;
    User user;

    @BeforeEach
    public void setupAccount() {
        homePage.clickAccountLink();
        accountPage = new AccountPage();
        accountPage.clickForgetPasswordButton();
        resetPasswordPage = new ResetPasswordPage();
        user = Users.getRandomUser();
    }

    @Test
    @DisplayName("Verify Reset Password page title text")
    public void assertTitleText() {
        Assertions.assertEquals("Reset password", resetPasswordPage.getTitleText());
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
