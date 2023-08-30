package com.example.myloginapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.example.myloginapp.utils.Locators;

public class LoginPage {
    private RemoteWebDriver driver;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id(Locators.usernameFieldId));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id(Locators.passwordFieldId));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.id(Locators.loginButtonId));
        loginButton.click();
    }

    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(By.id(Locators.logoutButtonId));
        logoutButton.click();
    }

}
