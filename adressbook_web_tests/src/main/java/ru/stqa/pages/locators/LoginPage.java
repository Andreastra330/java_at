package ru.stqa.pages.locators;

import org.openqa.selenium.WebDriver;
import ru.stqa.config.ConfigReader;
import ru.stqa.pages.BasePage;

public class LoginPage {
    private static WebDriver driver;
    private static BasePage basePage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.basePage = new BasePage(driver);
    }

    public void login() {
        driver.get(ConfigReader.getBaseUrl());
        basePage.findElement(Locators.USER_NAME_BTN).sendKeys(ConfigReader.getUsername());
        basePage.findElement(Locators.PASSWORD_BTN).sendKeys(ConfigReader.getPassword());
        basePage.findElement(Locators.LOGIN_BTN).click();
    }
}
