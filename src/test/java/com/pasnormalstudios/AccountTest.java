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
        accountPage.clickSubmitButton();
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    public void assertPasswordAlertMessage() {
        accountPage.setRandomEmailInput();
        accountPage.clickSubmitButton();
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }


}
