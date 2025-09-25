package ru.stqa.pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebDriver {

    public static WebDriver createChromeDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        return driver;
    }


}
