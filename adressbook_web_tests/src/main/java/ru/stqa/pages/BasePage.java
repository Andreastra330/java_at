package ru.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickIfElementNotPresent(By locatorToCheck, By locatorToClick) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locatorToCheck));
        } catch (TimeoutException exception) {
            driver.findElement(locatorToClick).click();
        }
    }

    public boolean isElementPresent(By locatorToCheck) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locatorToCheck));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }
}
