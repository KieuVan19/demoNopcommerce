package tests;

import base.BaseTest;
import com.nopcommerce.base.BasePage;
import com.nopcommerce.pages.HomePage;
import com.nopcommerce.pages.SearchResultPage;
import com.nopcommerce.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.nopcommerce.base.BasePage.navigateTo;
import static com.nopcommerce.pages.Navigation.homePage_EP;

public class SearchBarTests extends BaseTest {
    BasePage basePage;
    HomePage homePage;
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void initPages() {
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test(dataProvider = "keyWord")
    public void spaceTrim(String keyWord) {
        navigateTo(homePage_EP);

        homePage.setSearchKeys(keyWord);
        homePage.clickSearchButton();

        ArrayList<String> productTittles = searchResultPage.getFoundProductsTitle();

        for (String productTitle : productTittles) {
            Assert.assertTrue(productTitle.contains(keyWord.trim().toLowerCase()));
        }
    }

    @Test
    public void searchWithOnlySpaceCharacters() {
        navigateTo(homePage_EP);

        homePage.setSearchKeys("   ");// 3 space characters
        homePage.clickSearchButton();

        String actualResult = searchResultPage.getLengthErrorMessage();
        String expectedResult = "Search term minimum length is 3 characters";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void searchWithNonExistingKeyWord() {
        String keyWord = String.valueOf(System.currentTimeMillis());
        navigateTo(homePage_EP);
        homePage.setSearchKeys(keyWord);
        homePage.clickSearchButton();

        String actualResult = searchResultPage.getNoProductFoundMessage();
        String expectedResult = "No products were found that matched your criteria.";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void searchWithTwoCharactersKeyWord() {
        navigateTo(homePage_EP);
        String twoRandomChars = TestDataFactory.getTwoRandomChars();
        homePage.setSearchKeys(twoRandomChars);
        homePage.clickSearchButton();

        String actualResult = searchResultPage.getLengthErrorMessage();
        String expectedResult = "Search term minimum length is 3 characters";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void searchIsCaseInsensitive() {
        String keyWord = "htc";
        navigateTo(homePage_EP);

        homePage.setSearchKeys(keyWord.toLowerCase());
        homePage.clickSearchButton();

        ArrayList<String> productTittlesWithLowerCaseSearchKey = searchResultPage.getFoundProductsTitle();


        homePage.setSearchKeys(keyWord.toUpperCase());
        homePage.clickSearchButton();

        ArrayList<String> productTittlesWithUpperCaseSearchKey = searchResultPage.getFoundProductsTitle();
        Assert.assertEquals(productTittlesWithUpperCaseSearchKey, productTittlesWithLowerCaseSearchKey);
    }

    @DataProvider(name = "keyWord")
    public Object[] provideKeyWords() {
        return new Object[]{
                " laptop ",
                "asus lap",
                " Spectre XT Pro "
        };
    }
}
