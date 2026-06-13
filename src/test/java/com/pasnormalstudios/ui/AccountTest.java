package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.Users;
import com.pasnormalstudios.pages.AccountPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest extends BaseTest {
    AccountPage accountPage;
    User user;

    @BeforeEach
    public void setupAccount() {
        homePage.clickAccountLink();
        accountPage = new AccountPage();
        user = Users.getRandomUser();
    }

    @Test
    @DisplayName("Verify Account page title text")
    public void assertTitleText() {
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }

    @Test
    @DisplayName("Verify error message when logging in with invalid credentials")
    public void assertInvalidEmailPasswordMessage() {
        accountPage.setEmailPasswordAndClickLoginButton(user);
        Assertions.assertEquals("Invalid email or password", accountPage.getInvalidEmailPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when email field is blank")
    public void assertBlankEmailAlertMessage() {
        accountPage.setPasswordInput(user.getPassword());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when password field is blank")
    public void assertBlankPasswordAlertMessage() {
        accountPage.setEmailInput(user.getEmail());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify that the submit registration button is visible on create account form")
    public void assertSignUpButtonVisible() {
        accountPage.clickCreateAccountButton();
        Assertions.assertTrue(accountPage.isSubmitSignUpButtonVisible());
    }

    @Test
    @DisplayName("Verify that the submit login button is visible when switching back from create account form")
    public void assertLoginButtonVisible() {
        accountPage.clickCreateAccountButton();
        accountPage.clickLoginButton();
        Assertions.assertTrue(accountPage.isSubmitLoginButtonVisible());
    }
}
