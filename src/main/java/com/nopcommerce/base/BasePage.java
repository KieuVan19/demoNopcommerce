package com.nopcommerce.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BasePage {
    protected static WebDriver driver;
    private static WebDriverWait wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void navigateTo(String pageName) {
        driver.get(pageName);
    }

    public static boolean elementIsDisappeared(By locator, String attributeName, String value) {
        return wait.until(ExpectedConditions.attributeToBe(locator, attributeName, value));
    }

    public static WebElement waitUntilElementIsClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementIsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public ArrayList<WebElement> waitUntilElementsAreVisible(By locator) {
        return (ArrayList<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public ArrayList<String> getTexts(ArrayList<WebElement> elements) {
        ArrayList<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText().trim().toLowerCase());
        }
        return texts;
    }

    public void clickElement(By locator) {
        WebElement element = waitUntilElementIsClickable(locator);
        element.click();
    }

    public void setText(By locator, String text) {
        WebElement element = waitUntilElementIsClickable(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = waitUntilElementIsVisible(locator);
        return element.getText();
    }

    public boolean elementIsChecked(By locator) {
        WebElement element = waitUntilElementIsVisible(locator);
        return element.isSelected();
    }

    public void selectCheckBox(By Locator) {
        boolean isChecked = elementIsChecked(Locator);
        if (!isChecked) clickElement(Locator);
    }

    public void deSelectCheckBox(By Locator) {
        boolean isChecked = elementIsChecked(Locator);
        if (isChecked) clickElement(Locator);
    }

    public void selectDropDownOptionByText(By dropDownLocator, String text) {
        WebElement dropDownElement = waitUntilElementIsClickable(dropDownLocator);
        Select select = new Select(dropDownElement);
        select.selectByVisibleText(text);
    }
}


