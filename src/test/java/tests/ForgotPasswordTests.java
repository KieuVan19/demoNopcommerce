package tests;

import base.BaseTest;
import com.nopcommerce.base.BasePage;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.pages.Navigation;
import com.nopcommerce.pages.PasswordRecoveryPage;
import com.nopcommerce.pages.RegisterPage;
import com.nopcommerce.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.nopcommerce.base.BasePage.navigateTo;
import static com.nopcommerce.pages.Navigation.passwordRecovery_EP;

public class ForgotPasswordTests extends BaseTest {
    BasePage basePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    PasswordRecoveryPage passwordRecoveryPage;


    @BeforeMethod
    public void initPages() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
    }

    @Test
    public void recoverWithExistingUser() {
        String registeredEmail = TestDataFactory.getEmail();
        // register a new user
        navigateTo(Navigation.register_EP);
        registerPage.selectGender("male");
        registerPage.setFirstName(TestDataFactory.getFirstName());
        registerPage.setLastName(TestDataFactory.getLastName());
        registerPage.setEmail(registeredEmail);
        registerPage.setCompanyName("Tech Paramount");
        registerPage.selectNewsLetter();
        registerPage.setPassword(TestDataFactory.getPassword());
        registerPage.setConfirmPassword(TestDataFactory.getConfirmPassword());
        registerPage.clickRegisterButton();
        driver.manage().deleteAllCookies();
        // Recover password for user

        navigateTo(passwordRecovery_EP);
        passwordRecoveryPage.setEmailAddress(registeredEmail);
        passwordRecoveryPage.clickRecoverButton();

        String expectedResult = "Email with instructions has been sent to you.";
        String actualResult = passwordRecoveryPage.getMessage("success");

        System.out.println(registeredEmail);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void recoverWithNonExistingUser() {
        String nonExistingEmail = TestDataFactory.getEmail();
        navigateTo(passwordRecovery_EP);
        passwordRecoveryPage.setEmailAddress(nonExistingEmail);
        passwordRecoveryPage.clickRecoverButton();

        String expectedResult = "Email not found.";
        String actualResult = passwordRecoveryPage.getMessage("error");

        Assert.assertEquals(actualResult, expectedResult);

    }
}
