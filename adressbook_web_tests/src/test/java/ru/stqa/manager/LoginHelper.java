package ru.stqa.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String user, String password) {
        typeWithClear(By.name("user"), user);
        typeWithClear(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}

