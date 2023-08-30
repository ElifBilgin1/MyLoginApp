package com.example.myloginapp.tests;

import com.example.myloginapp.pages.LoginPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.example.myloginapp.utils.Locators;
import com.example.myloginapp.utils.Configs;

public class LoginTest {

    private RemoteWebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", Configs.deviceName);
        capabilities.setCapability("platformName", Configs.platformName);
        capabilities.setCapability("appPackage", Configs.appPackage);
        capabilities.setCapability("appActivity", Configs.appActivity);

        driver = new RemoteWebDriver(new URL(Configs.url), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccess() {
        loginPage.enterUsername("admin@fireflyon.com");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();

        String welcomeText = driver.findElement(By.id(Locators.welcomeTextId)).getText();
        System.out.println("welcomeText: " + welcomeText);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assert welcomeText.equals("Welcome Admin");
        System.out.println("testLoginSuccess finished successfully.");

    }

    @Test
    public void testLoginEmptyValidation() {
        loginPage.clickLoginButton();

        String warningMessage = driver.findElement(By.id(Locators.warningMessageId)).getText();
        System.out.println("warningMessage: " + warningMessage);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assert warningMessage.contains("should not be empty");
        System.out.println("testLoginEmptyValidation finished successfully.");

    }

    @Test
    public void testLoginEmailValidation() {
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();

        String warningMessage = driver.findElement(By.id(Locators.warningMessageId)).getText();
        System.out.println("warningMessage: " + warningMessage);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assert warningMessage.contains("Email format not correct");
        System.out.println("testLoginEmailValidation finished successfully.");

    }

    @Test
    public void testLoginPasswordLengthValidation() {
        loginPage.enterUsername("admin@fireflyon.com");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        String warningMessage = driver.findElement(By.id(Locators.warningMessageId)).getText();
        System.out.println("warningMessage: " + warningMessage);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assert warningMessage.contains("Password should be minimum 5 characters");
        System.out.println("testLoginPasswordLengthValidation finished successfully.");

    }

    @Test
    public void testLoginPasswordValidation() {
        loginPage.enterUsername("admin@fireflyon.com");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        String warningMessage = driver.findElement(By.id(Locators.warningMessageId)).getText();
        System.out.println("warningMessage: " + warningMessage);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        assert warningMessage.contains("LOGIN FAILED!");
        System.out.println("testLoginPasswordValidation finished successfully.");

    }

    @Test
    public void testLogoutSuccess() {
        loginPage.enterUsername("admin@fireflyon.com");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();

        String welcomeText = driver.findElement(By.id(Locators.welcomeTextId)).getText();
        System.out.println("welcomeText: " + welcomeText);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage.clickLogoutButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String signInText = driver.findElement(By.id(Locators.signinTextId)).getText();

        assert signInText.equals("Sign in");
        System.out.println("testLogoutSuccess finished successfully.");

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
