package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.User;
import com.pasnormalstudios.data.Users;
import com.pasnormalstudios.pages.AccountPage;
import com.pasnormalstudios.pages.ResetPasswordPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("Account Management")
@Feature("Password Recovery")
@Tag("regress")
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
    @Description("Check that the main header text on the 'Reset Password' page is displayed correctly.")
    @Severity(SeverityLevel.TRIVIAL)
    @Tag("ui")
    public void assertTitleText() {
        Assertions.assertEquals("Reset password", resetPasswordPage.getTitleText());
    }

    @Test
    @DisplayName("Verify validation alert message when email field is blank")
    @Description("Form validation check: verify 'Email is required' alert when attempting to submit an empty email field.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("validation")
    public void assertEmailAlertMessage() {
        resetPasswordPage.clickSubmitButton();
        Assertions.assertEquals("Email is required", resetPasswordPage.getEmailAlertMessageText());
    }

    @Test
    @DisplayName("Verify success message after submitting valid email for reset")
    @Description("Verify that the information message confirms instructions have been sent when a valid email is submitted.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    public void assertResetPasswordInfoMessage() {
        resetPasswordPage.setEmailAndClickSubmitButton(user);
        Assertions.assertEquals("Reset instructions have been sent to your email", resetPasswordPage.getResetPasswordInfoMessageText());
    }

    @Test
    @DisplayName("Verify navigation back to login page from password recovery success page")
    @Description("Verify that the 'Back to Login' button properly redirects the user back to the main Account/Login form.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("ui")
    public void assertBackToLoginButton() {
        resetPasswordPage.setEmailAndClickSubmitButton(user);
        resetPasswordPage.clickBackToLoginButton();
        Assertions.assertEquals("Create your International Cycling Club account", accountPage.getTitleText());
    }
}