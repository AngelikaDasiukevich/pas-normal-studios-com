package com.pasnormalstudios;

import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest extends BaseTest {
    AccountPage accountPage;
    User user;

    @BeforeEach
    public void setupAccount() {
        accountPage = new AccountPage();
        homePage.clickAccountLink();
        user = UserFactory.createValidUser();
    }

    @Test
    public void assertTitleText() {
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }

    @Test
    public void assertInvalidEmailPasswordMessage() {
        accountPage.setEmailPasswordAndClickLoginButton(user);
        Assertions.assertEquals("Invalid email or password", accountPage.getInvalidEmailPasswordAlertMessageText());
    }

    @Test
    public void assertBlankEmailAlertMessage() {
        accountPage.setPasswordInput(user.getPassword());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    public void assertBlankPasswordAlertMessage() {
        accountPage.setEmailInput(user.getEmail());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }

    @Test
    public void assertSignUpButtonVisible() {
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
