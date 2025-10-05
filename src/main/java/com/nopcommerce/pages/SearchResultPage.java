package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.nopcommerce.utils.PriceBar.moveSliderLeftHandle;
import static com.nopcommerce.utils.PriceBar.moveSliderRightHandle;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    By productTitle_TXT = By.xpath("//h2[@class = 'product-title']/a");
    By minimumLengthError_MSG = By.xpath("//div[@class ='warning']");
    By noProductFound_MSG = By.xpath("//div[@class ='no-result']");
    By order_DDL = By.id("products-orderby");
    By productPrice_TXT = By.xpath("//span[@class='price actual-price']");
    static By loading_SYMBOL = By.xpath("//div[@class ='ajax-products-busy']");

    By priceRange_BAR = By.xpath("//div[@id='price-range-slider']");
    By priceBarLeft_HDL = By.xpath("//div[@id='price-range-slider']/*[2]");
    By priceBarRight_HDL = By.xpath("//div[@id='price-range-slider']/*[3]");
    By minPrice_LBL = By.xpath("//div[@class='selected-price-range']/span[@class='from']");
    By maxPrice_LBL = By.xpath("//div[@class='selected-price-range']/span[@class='to']");

    public void sortBy(String order) {
        selectDropDownOptionByText(order_DDL, order);
    }

    public String getLengthErrorMessage() {
        return getText(minimumLengthError_MSG);
    }

    public static boolean loadingSymbolIsDisappeared() {
        return elementIsDisappeared(loading_SYMBOL, "style", "display: none;");
    }

    public String getNoProductFoundMessage() {
        return getText(noProductFound_MSG);
    }

    public ArrayList<String> getFoundProductsTitle() {
        ArrayList<WebElement> elements = waitUntilElementsAreVisible(productTitle_TXT);
        return getTexts(elements);
    }

    public ArrayList<Double> getFoundProductsPrice() {
        ArrayList<String> priceOfElements = getTexts(waitUntilElementsAreVisible(productPrice_TXT));
        ArrayList<Double> pricesInNumber = new ArrayList<>();
        for (String priceElement : priceOfElements) {
            pricesInNumber.add(Double.valueOf(priceElement.replace("$", "")
                    .replace(",", "").trim()));
        }
        return pricesInNumber;
    }

    public void movePriceBarLeftHandle(int fromTargetPrice) {
        moveSliderLeftHandle(priceBarLeft_HDL, priceRange_BAR, fromTargetPrice);
    }

    public void movePriceBarRightHandle(int toTargetPrice) {
        moveSliderRightHandle(priceBarRight_HDL, priceRange_BAR, toTargetPrice);
    }

    public String getMinPrice_LBL() {
        return getText(minPrice_LBL);
    }

    public String getMaxPrice_LBL() {
        return getText(maxPrice_LBL);
    }
}
