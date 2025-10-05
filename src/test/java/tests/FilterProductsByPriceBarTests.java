package tests;

import base.BaseTest;
import com.nopcommerce.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.nopcommerce.base.BasePage.navigateTo;
import static com.nopcommerce.pages.Navigation.desktopsProduct_EP;
import static com.nopcommerce.pages.SearchResultPage.loadingSymbolIsDisappeared;

public class FilterProductsByPriceBarTests extends BaseTest {
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void initPages() {
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void productIsFilteredFromPrice() {
        navigateTo(desktopsProduct_EP);
        int fromTargetPrice = 1000;
        searchResultPage.movePriceBarLeftHandle(fromTargetPrice);
        loadingSymbolIsDisappeared();
        ArrayList<Double> productsPrice = searchResultPage.getFoundProductsPrice();
        System.out.println("fromPriceLabel is: " + searchResultPage.getMinPrice_LBL());
        System.out.println(productsPrice);
        for (Double p : productsPrice) {
            Assert.assertTrue(p >= (double) fromTargetPrice);
        }
    }
    @Test
    public void productIsFilteredToPrice() {
        navigateTo(desktopsProduct_EP);
        int toTargetPrice = 1000;
        searchResultPage.movePriceBarRightHandle(toTargetPrice);
        System.out.println(searchResultPage.getMaxPrice_LBL());
        loadingSymbolIsDisappeared();
        ArrayList<Double> productsPrice = searchResultPage.getFoundProductsPrice();
        System.out.println("Price of product: " + productsPrice);
        System.out.println("toPriceLabel is: " + searchResultPage.getMaxPrice_LBL());
        for (Double p : productsPrice) {
            Assert.assertTrue(p <= (double) toTargetPrice);
        }
    }
}
