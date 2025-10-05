package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By forgotPassword_BTN = By.xpath("//a[@href ='/passwordrecovery']");

    public void clickForgotPasswordButton() {
        clickElement(forgotPassword_BTN);
    }
}
