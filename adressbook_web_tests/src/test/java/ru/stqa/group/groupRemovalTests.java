package ru.stqa.group;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.stqa.pages.BasePage;
import ru.stqa.pages.GroupPage;
import ru.stqa.pages.MyWebDriver;
import ru.stqa.pages.locators.Locators;
import ru.stqa.pages.locators.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class groupRemovalTests {

    private static WebDriver driver;
    private static BasePage basePage;
    private static LoginPage loginPage;
    private static GroupPage groupPage;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = MyWebDriver.createChromeDriver();
            basePage = new BasePage(driver);
            loginPage = new LoginPage(driver);
            groupPage = new GroupPage(driver);
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            loginPage.login();
        }
    }


    @Test
    @Order(3)
    @DisplayName("Удаление группы")
    public void removeGroup() {
        basePage.clickIfElementNotPresent(Locators.NEW_BTN, Locators.GROUPS_BTN);
        if (!basePage.isElementPresent(Locators.SELECT_GOURP_BTN)) {
            basePage.findElement(Locators.NEW_BTN).click();
            groupPage.createGroup("if gourps clear", "if gourps clear", "if gourps clear");
        }
        basePage.findElement(Locators.SELECT_GOURP_BTN).click();
        basePage.findElement(Locators.DELETE_BTN).click();
        basePage.findElement(Locators.RETURN_GROUP_PAGE_BTN).click();

    }

}

