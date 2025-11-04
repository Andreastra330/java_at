package ru.stqa.mantis.manager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.mantis.config.ConfigReader;

import java.time.Duration;
import java.util.List;

public class ApplicationManager {
    protected WebDriver driver;
    private String browser;
    private SessionHelper sessionHelper;

//    public void initialBrowser(String browser) {
//        this.browser = browser;
//
//
//    }
//    //session().login(ConfigReader.getUsername(), ConfigReader.getPassword());
//    public WebDriver driver(String property){
//        String baseUrl = ConfigReader.getBaseUrl();
//        if(driver == null)
//        {
//            if ("firefox".equals(browser)) {
//                driver = new FirefoxDriver();
//            } else if ("chrome".equals(browser)) {
//                driver = new ChromeDriver();
//            } else {
//                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
//            }
//            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
//            driver.get(baseUrl);
//            driver.manage().window().setSize(new Dimension(1920, 1080));
//        }
//        return driver;
//    }

    public void initialBrowser(String browser) {
        String baseUrl = ConfigReader.getBaseUrl();
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(baseUrl);
            driver.manage().window().setSize(new Dimension(1920, 1080));
            //session().login(ConfigReader.getUsername(), ConfigReader.getPassword());
        }
    }

    public SessionHelper session(){
        if (sessionHelper == null){
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }
    public boolean isElementPresent(By locator) {
        try {
            Thread.sleep(100);
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement findEl(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement findElIfClickable(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> findElements(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void scroll(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
