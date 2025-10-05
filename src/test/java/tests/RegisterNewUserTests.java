package tests;

import base.BaseTest;
import com.nopcommerce.base.BasePage;
import com.nopcommerce.pages.Navigation;
import com.nopcommerce.pages.RegisterPage;
import com.nopcommerce.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.nopcommerce.base.BasePage.navigateTo;

public class RegisterNewUserTests extends BaseTest {
    BasePage basePage;
    RegisterPage registerPage;

    @BeforeMethod
    public void initPages() {
        basePage = new BasePage(driver);
        registerPage = new RegisterPage(driver);

    }

    @Test
    public void registerWithValidDataFilledForAllFields() {

        navigateTo(Navigation.register_EP);
        registerPage.selectGender("male");
        registerPage.setFirstName(TestDataFactory.getFirstName());
        registerPage.setLastName(TestDataFactory.getLastName());
        registerPage.setEmail(TestDataFactory.getEmail());
        registerPage.setCompanyName("Tech Paramount");
        registerPage.selectNewsLetter();
        registerPage.setPassword(TestDataFactory.getPassword());
        registerPage.setConfirmPassword(TestDataFactory.getConfirmPassword());
        registerPage.clickRegisterButton();

        String actualResult = registerPage.getRegisterSuccessResult();
        String expectedResult = "Your registration completed";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void registerWithValidDataFilledForOnlyMandatoryFields() {

        navigateTo(Navigation.register_EP);
        registerPage.setFirstName(TestDataFactory.getFirstName());
        registerPage.setLastName(TestDataFactory.getLastName());
        registerPage.setEmail(TestDataFactory.getEmail());
        registerPage.setPassword(TestDataFactory.getPassword());
        registerPage.setConfirmPassword(TestDataFactory.getConfirmPassword());
        registerPage.clickRegisterButton();

        String actualResult = registerPage.getRegisterSuccessResult();
        String expectedResult = "Your registration completed";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void registerWithAllFieldsEmpty() {

        navigateTo(Navigation.register_EP);
        registerPage.clickRegisterButton();

        String actualResultForFirstName = registerPage.getErrorMessage("firstName");
        String actualResultForLastName = registerPage.getErrorMessage("lastName");
        String actualResultForEmail = registerPage.getErrorMessage("email");
        String actualResultForPassword = registerPage.getErrorMessage("password");

        Assert.assertEquals(actualResultForFirstName, "First name is required.");
        Assert.assertEquals(actualResultForLastName, "Last name is required.");
        Assert.assertEquals(actualResultForEmail, "Email is required.");
        Assert.assertEquals(actualResultForPassword, "Password is required.");
    }

    @Test
    public void registerWithRegisteredEmail() {
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

        // register with email used for user above
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

        String actualResult = registerPage.getErrorMessage("sameEmail");
        String expectedResult = "The specified email already exists";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void registerWithMisMatchedPassword() {
        navigateTo(Navigation.register_EP);
        registerPage.selectGender("male");
        registerPage.setFirstName(TestDataFactory.getFirstName());
        registerPage.setLastName(TestDataFactory.getLastName());
        registerPage.setEmail(TestDataFactory.getEmail());
        registerPage.setCompanyName("Tech Paramount");
        registerPage.selectNewsLetter();
        registerPage.setPassword(TestDataFactory.getPassword());
        registerPage.setConfirmPassword(TestDataFactory.getConfirmPassword() + System.currentTimeMillis());
        registerPage.clickRegisterButton();

        String actualResult = registerPage.getErrorMessage("mismatchedPassword");
        String expectedResult = "The password and confirmation password do not match.";
        Assert.assertEquals(actualResult, expectedResult);

    }
}
