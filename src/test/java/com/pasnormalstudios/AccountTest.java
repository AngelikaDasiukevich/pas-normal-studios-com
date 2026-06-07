package com.pasnormalstudios;

import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.UserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest extends BaseTest {
    AccountPage accountPage;
    User user;
    private static final Logger log = LogManager.getLogger(AccountTest.class);

    @BeforeEach
    public void setupAccount() {
        accountPage = new AccountPage();
        log.info("Navigating to the account page via the home page.");
        homePage.clickAccountLink();
        user = UserFactory.createValidUser();
        log.info("Test user generated: Email [{}], Password [{}]", user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Verify Account page title text")
    public void assertTitleText() {
        log.info("Verifying title match. Expected: '{}', Actual: '{}'", "Create your " +
                "International Cycling Club account", accountPage.getTitleText());
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }

    @Test
    @DisplayName("Verify error message when logging in with invalid credentials")
    public void assertInvalidEmailPasswordMessage() {
        log.info("Entering user credentials and clicking the login button.");
        accountPage.setEmailPasswordAndClickLoginButton(user);
        log.info("Verifying error text. Expected: '{}', Actual: '{}'", "Invalid email or password",
                accountPage.getInvalidEmailPasswordAlertMessageText());
        Assertions.assertEquals("Invalid email or password", accountPage.getInvalidEmailPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when email field is blank")
    public void assertBlankEmailAlertMessage() {
        log.info("Entering password without email and submitting the form.");
        accountPage.setPasswordInput(user.getPassword());
        accountPage.clickSubmitLoginButton();
        log.info("Verifying validation message for Email. Expected: '{}', Actual: '{}'",
                "Email is required", accountPage.getEmailAlertMessageText());
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when password field is blank")
    public void assertBlankPasswordAlertMessage() {
        log.info("Entering email without password and submitting the form.");
        accountPage.setEmailInput(user.getEmail());
        accountPage.clickSubmitLoginButton();
        log.info("Verifying validation message for Password. Expected: '{}', Actual: '{}'",
                "Password is required", accountPage.getPasswordAlertMessageText());
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify that the submit registration button is visible on create account form")
    public void assertSignUpButtonVisible() {
        log.info("Navigating to the create account form.");
        accountPage.clickCreateAccountButton();
        log.info("Verifying the registration form submit button visibility.");
        Assertions.assertTrue(accountPage.isSubmitSignUpButtonVisible());
    }

    @Test
    @DisplayName("Verify that the submit login button is visible when switching back from create account form")
    public void assertLoginButtonVisible() {
        log.info("Navigating to the create account form and clicking the 'Login' button.");
        accountPage.clickCreateAccountButton();
        accountPage.clickLoginButton();
        log.info("Verifying the authorization form submit button visibility.");
        Assertions.assertTrue(accountPage.isSubmitLoginButtonVisible());
    }
}
