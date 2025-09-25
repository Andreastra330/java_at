package ru.stqa.pages;

import org.openqa.selenium.WebDriver;
import ru.stqa.pages.locators.Locators;

public class GroupPage {
    private static WebDriver driver;
    private static BasePage basePage;

    public GroupPage(WebDriver driver) {
        this.driver = driver;
        this.basePage = new BasePage(driver);
    }

    public void createGroup(String name, String header, String footer) {
        basePage.findElement(Locators.GROUP_NAME_BTN).sendKeys(name);
        basePage.findElement(Locators.GROUP_HEADER_BTN).sendKeys(header);
        basePage.findElement(Locators.GROUP_FOOTER_BTN).sendKeys(footer);
        basePage.findElement(Locators.BTN_SUMBIT).click();
        basePage.findElement(Locators.RETURN_GROUP_PAGE_BTN).click();
    }

}
