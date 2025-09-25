package ru.stqa.pages.locators;

import org.openqa.selenium.By;

public class Locators {
    public static final By USER_NAME_BTN = By.xpath("//input[@name=\'user\']");
    public static final By PASSWORD_BTN = By.xpath("//input[@name=\'pass\']");
    public static final By LOGIN_BTN = By.xpath("//input[@value=\'Login\']");
    public static final By GROUPS_BTN = By.linkText("groups");
    public static final By NEW_BTN = By.name("new");
    public static final By GROUP_NAME_BTN = By.name("group_name");
    public static final By GROUP_HEADER_BTN = By.name("group_header");
    public static final By GROUP_FOOTER_BTN = By.name("group_footer");
    public static final By BTN_SUMBIT = By.name("submit");
    public static final By RETURN_GROUP_PAGE_BTN = By.xpath("//a[normalize-space(text())='group page']");
    public static final By SELECT_GOURP_BTN = By.name("selected[]");
    public static final By DELETE_BTN = By.name("delete");
}
