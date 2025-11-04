package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void typeWithClear(By locator, String text) {
        click(locator);
        manager.findEl(locator, 3).clear();
        manager.findEl(locator, 3).sendKeys(text);
    }

    protected void click(By locator) {
        manager.findEl(locator, 5).click();
    }

    protected void clickIfClickable(By locator){
        manager.findElIfClickable(locator,5).click();
    }

    protected void type(By locator, String text) {
        manager.findEl(locator, 3).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        if (!file.isEmpty()) {
            manager.findEl(locator, 3).sendKeys(Paths.get(file).toAbsolutePath().toString());
        } else {
        }
    }

    protected boolean isElementPresent(By locator){
        return manager.driver.findElements(locator).isEmpty();
    }
}
