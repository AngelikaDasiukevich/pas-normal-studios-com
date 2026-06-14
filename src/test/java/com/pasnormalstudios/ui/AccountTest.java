package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.Users;
import com.pasnormalstudios.pages.AccountPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("Account Management")
@Feature("Login and Registration")
@Tag("regression")
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
    @Description("Check that the main header text on the account page is displayed correctly.")
    @Severity(SeverityLevel.TRIVIAL)
    @Tag("ui")
    public void assertTitleText() {
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }

    @Test
    @DisplayName("Verify error message when logging in with invalid credentials")
    @Description("Verify that the 'Invalid email or password' error alert appears when submitting random invalid data.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    public void assertInvalidEmailPasswordMessage() {
        accountPage.setEmailPasswordAndClickLoginButton(user);
        Assertions.assertEquals("Invalid email or password", accountPage.getInvalidEmailPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when email field is blank")
    @Description("Form validation check: verify 'Email is required' alert when the email input field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("validation")
    public void assertBlankEmailAlertMessage() {
        accountPage.setPasswordInput(user.getPassword());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Email is required", accountPage.getEmailAlertMessageText());
    }

    @Test
    @DisplayName("Verify validation alert message when password field is blank")
    @Description("Form validation check: verify 'Password is required' alert when the password input field is left empty.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("validation")
    public void assertBlankPasswordAlertMessage() {
        accountPage.setEmailInput(user.getEmail());
        accountPage.clickSubmitLoginButton();
        Assertions.assertEquals("Password is required", accountPage.getPasswordAlertMessageText());
    }

    @Test
    @DisplayName("Verify that the submit registration button is visible on create account form")
    @Description("Verify UI toggle: ensure the registration submit button becomes visible after switching to the 'Create Account' form.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("ui")
    public void assertSignUpButtonVisible() {
        accountPage.clickCreateAccountButton();
        Assertions.assertTrue(accountPage.isSubmitSignUpButtonVisible());
    }

    @Test
    @DisplayName("Verify that the submit login button is visible when switching back from create account form")
    @Description("Verify UI toggle: ensure the login button is properly restored when switching back from the registration form.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("ui")
    public void assertLoginButtonVisible() {
        accountPage.clickCreateAccountButton();
        accountPage.clickLoginButton();
        Assertions.assertTrue(accountPage.isSubmitLoginButtonVisible());
    }
}