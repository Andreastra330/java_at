package ru.stqa.manager;

import org.openqa.selenium.By;

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

    protected void type(By locator, String text) {
        manager.findEl(locator, 3).sendKeys(text);
    }


}
