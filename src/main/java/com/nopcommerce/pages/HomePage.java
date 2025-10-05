package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By searchBar_TXT = By.xpath("//input[@class = 'search-box-text ui-autocomplete-input']");
    By search_BTN = By.xpath("//button[@type = 'submit']");

    public void clickSearchButton() {
        clickElement(search_BTN);
    }

    public void setSearchKeys(String keys) {
        setText(searchBar_TXT, keys);
    }

}
