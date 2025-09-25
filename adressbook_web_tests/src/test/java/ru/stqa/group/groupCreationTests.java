package ru.stqa.group;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.stqa.pages.BasePage;
import ru.stqa.pages.GroupPage;
import ru.stqa.pages.MyWebDriver;
import ru.stqa.pages.locators.Locators;
import ru.stqa.pages.locators.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class groupCreationTests {

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
    @Order(1)
    @DisplayName("Создание группы")
    public void canCreateGroup() {
        basePage.clickIfElementNotPresent(Locators.NEW_BTN, Locators.GROUPS_BTN);
        basePage.findElement(Locators.NEW_BTN).click();
        groupPage.createGroup("My name", "My header", "My footer");
    }

    @Test
    @Order(2)
    @DisplayName("Создание группы с пустыми полями")
    public void canCreateGroupWithEmptyName() {
        basePage.clickIfElementNotPresent(Locators.NEW_BTN, Locators.GROUPS_BTN);
        basePage.findElement(Locators.NEW_BTN).click();
        groupPage.createGroup("", "", "");
    }

}

