package com.pasnormalstudios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest extends BaseTest {
    AccountPage accountPage;

    @BeforeEach
    public void setupAccount() {
        accountPage = new AccountPage(driver);
        homePage.clickAccountLink();
    }

    @Test
    public void assertTitleText() {
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }

    @Test
    public void assertInvalidEmailPasswordMessage() {
        accountPage.setRandomEmailPasswordAndClickLoginButton();
        Assertions.assertEquals("Invalid email or password", accountPage.getInvalidEmailPasswordAlertMessageText());
    }

    @Test
    public void assertEmailAlertMessage() {
        accountPage.setRandomPasswordInput();
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    public void assertPasswordAlertMessage() {
        accountPage.setRandomEmailInput();
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }

    @Test
    public void assertCreateAccountButtonVisible() {
        accountPage.clickCreateAccountButton();
        Assertions.assertTrue(accountPage.isSubmitSignUpButtonVisible());
    }

    @Test
    public void assertLoginButtonVisible() {
        accountPage.clickCreateAccountButton();
        accountPage.clickLoginButton();
        Assertions.assertTrue(accountPage.isSubmitLoginButtonVisible());
    }
}
