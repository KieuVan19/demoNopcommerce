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
}
