package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    By emailAddress_TXT = By.id("Email");
    By recover_BTN = By.name("send-email");
    By error_MSG = By.xpath("//div[@class='bar-notification error']/p");
    By success_MSG = By.xpath("//div[@class='bar-notification success']/p");

    public void setEmailAddress(String email) {
        setText(emailAddress_TXT, email);
    }

    public void clickRecoverButton() {
        clickElement(recover_BTN);
    }

    public String getMessage(String message) {
        if (message.equalsIgnoreCase("error"))
            return getText(error_MSG);
        else if (message.equalsIgnoreCase("success"))
            return getText(success_MSG);
        else return "There is no message";
    }
}
