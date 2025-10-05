package tests;

import base.BaseTest;
import com.nopcommerce.pages.HomePage;
import com.nopcommerce.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.nopcommerce.base.BasePage.navigateTo;
import static com.nopcommerce.pages.Navigation.notebooksProduct_EP;
import static com.nopcommerce.pages.SearchResultPage.loadingSymbolIsDisappeared;

public class FilterProductsByDropDownListTests extends BaseTest {
    HomePage homepage;
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void initPages() {
        homepage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void productIsSortedByPriceInAscendingOrder() {
        navigateTo(notebooksProduct_EP);

        ArrayList<Double> beforeSorting = searchResultPage.getFoundProductsPrice();
        System.out.println(beforeSorting);
        searchResultPage.sortBy("Price: Low to High");

        if (loadingSymbolIsDisappeared()) {
            ArrayList<Double> afterSorting = searchResultPage.getFoundProductsPrice();
            System.out.println(afterSorting);

            for (int i = 0; i < afterSorting.size() - 1; i++) {
                System.out.println("Round " + i);
                System.out.println("i: " + afterSorting.get(i));
                System.out.println("i+1: " + afterSorting.get(i + 1));
                Assert.assertTrue(afterSorting.get(i) <= afterSorting.get(i + 1));
            }
        } else System.out.println("Sort option is not applied to products");
    }

    @Test
    public void productIsSortedByPriceInDescendingOrder() {
        navigateTo(notebooksProduct_EP);
        searchResultPage.sortBy("Price: High to Low");

        if (loadingSymbolIsDisappeared()) {
            ArrayList<Double> productsPrice = searchResultPage.getFoundProductsPrice();
            System.out.println(productsPrice);

            for (int i = 0; i < productsPrice.size() - 1; i++) {
                System.out.println("Round " + i);
                System.out.println("i: " + productsPrice.get(i));
                System.out.println("i+1: " + productsPrice.get(i + 1));
                Assert.assertTrue(productsPrice.get(i) >= productsPrice.get(i + 1));
            }
        } else System.out.println("Sort option is not applied to products");
    }

    @Test
    public void productIsSortedFromAtoZ() {
        navigateTo(notebooksProduct_EP);
        searchResultPage.sortBy("Name: A to Z");

        if (loadingSymbolIsDisappeared()) {
            ArrayList<String> productsTitle = searchResultPage.getFoundProductsTitle();
            System.out.println(productsTitle);
            ArrayList<String> productsTitle1 = new ArrayList<>(productsTitle);
            Collections.sort(productsTitle1);
            Assert.assertEquals(productsTitle, productsTitle1);

        } else System.out.println("Sort option is not applied to products");
    }

    @Test
    public void productIsSortedFromZtoA() {
        navigateTo(notebooksProduct_EP);
        searchResultPage.sortBy("Name: Z to A");

        if (loadingSymbolIsDisappeared()) {
            ArrayList<String> productsTitle = searchResultPage.getFoundProductsTitle();
            System.out.println(productsTitle);
            ArrayList<String> productsTitle1 = new ArrayList<>(productsTitle);
            productsTitle1.sort(Collections.reverseOrder());
            Assert.assertEquals(productsTitle, productsTitle1);

        } else System.out.println("Sort option is not applied to products");
    }
}
