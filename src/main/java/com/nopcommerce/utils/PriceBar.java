package com.nopcommerce.utils;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PriceBar extends BasePage {
    public PriceBar(WebDriver driver) {
        super(driver);
    }

    private static final int sliderMaxVal = 10000;

    private static double calculateWidthPerUnitPrice(int elementWidth) {
        int sliderMinVal = 0;
        return (double) elementWidth / (sliderMaxVal - sliderMinVal);
    }

    public static void moveSliderLeftHandle(By sliderHandle, By sliderLocator, int targetPrice) {
        int sliderWidth = waitUntilElementIsClickable(sliderLocator).getSize().getWidth();
        double unitWidth = calculateWidthPerUnitPrice(sliderWidth);
        double offSet = targetPrice * unitWidth;
        WebElement sliderHandleElement = waitUntilElementIsClickable(sliderHandle);
        Actions action = new Actions(driver);
        action.clickAndHold(sliderHandleElement).moveByOffset((int) offSet, 0).release().perform();
    }

    public static void moveSliderRightHandle(By sliderHandle, By sliderLocator, int targetPrice) {
        int sliderWidth = waitUntilElementIsClickable(sliderLocator).getSize().getWidth();
        System.out.println("sliderWidth: " + sliderWidth);
        double unitWidth = calculateWidthPerUnitPrice(sliderWidth);
        double offSet = (sliderMaxVal - targetPrice) * unitWidth;
        WebElement sliderHandleElement = waitUntilElementIsClickable(sliderHandle);
        Actions action = new Actions(driver);
        action.clickAndHold(sliderHandleElement).moveByOffset(-(int) offSet, 0).release().perform();
    }

}
