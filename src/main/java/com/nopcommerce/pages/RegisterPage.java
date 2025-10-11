package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String printDOM() {
        return super.driver.getPageSource();
    }

    By gender_M_BTN = By.id("gender-male");
    By gender_F_BTN = By.id("gender-female");
    By firstName_TXT = By.id("FirstName");
    By lastName_TXT = By.id("LastName");
    By email_TXT = By.id("Email");
    By companyName_TXT = By.id("Company");
    By newsLetter_BTN = By.id("Newsletter");
    By password_TXT = By.id("Password");
    By confirmPassword_TXT = By.id("ConfirmPassword");
    By register_BTN = By.id("register-button");
    By registerSuccessResult_TXT = By.xpath("//div[text()='Your registration completed']");
    By firstNameIsRequired_TXT = By.id("FirstName-error");
    By lastNameIsRequired_TXT = By.id("LastName-error");
    By emailIsRequired_TXT = By.id("Email-error");
    By passwordIsRequired_TXT = By.id("ConfirmPassword-error");
    By misMatchedPassword_TXT = By.id("ConfirmPassword-error");
    By sameEmail_TXT = By.xpath("//li[text()='The specified email already exists']");

    public String getRegisterSuccessResult() {
        return getText(registerSuccessResult_TXT);
    }

    public String getErrorMessage(String fieldName) {
        if (fieldName.equalsIgnoreCase("firstName"))
            return getText(firstNameIsRequired_TXT);
        else if (fieldName.equalsIgnoreCase("lastName"))
            return getText(lastNameIsRequired_TXT);
        else if (fieldName.equalsIgnoreCase("email"))
            return getText(emailIsRequired_TXT);
        else if (fieldName.equalsIgnoreCase("password"))
            return getText(passwordIsRequired_TXT);
        else if (fieldName.equalsIgnoreCase("misMatchedPassword"))
            return getText(misMatchedPassword_TXT);
        else if (fieldName.equalsIgnoreCase("sameEmail"))
            return getText(sameEmail_TXT);
        else
            return ("No error message for " + fieldName);
    }


    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            clickElement(gender_M_BTN);
        } else clickElement(gender_F_BTN);
    }

    public void setFirstName(String firstName) {
        setText(firstName_TXT, firstName);
    }

    public void setLastName(String lastName) {
        setText(lastName_TXT, lastName);
    }

    public void setEmail(String email) {
        setText(email_TXT, email);
    }

    public void setCompanyName(String companyName) {
        setText(companyName_TXT, companyName);
    }

    public void selectNewsLetter() {
        selectCheckBox(newsLetter_BTN);
    }

    public void deSelectNewsLetter() {
        deSelectCheckBox(newsLetter_BTN);
    }

    public void setPassword(String password) {
        setText(password_TXT, password);
    }

    public void setConfirmPassword(String confirmPassword) {
        setText(confirmPassword_TXT, confirmPassword);
    }

    public void clickRegisterButton() {
        clickElement(register_BTN);
    }
}
