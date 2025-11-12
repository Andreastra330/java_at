package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends  HelperBase{

    public SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"),user);
        click(By.xpath("//input[@type = 'submit']"));
        type(By.name("password"),password);
        click(By.xpath("//input[@type = 'submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.xpath("//span[class='user-info']"));
    }

    public void register(String username,String email)
    {
        click(By.xpath("//a[normalize-space(text())='Signup for a new account']"));
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.xpath("//input[@type='submit']"));
        if(!manager.isElementPresent(By.xpath("//strong[normalize-space(text())='Account registration processed.']")))
        {
            throw new IllegalArgumentException("Аккаунт не зарегистрирован");
        }
    }

    public void updateAccountAfterRegister(String name,String password){
        type(By.id("realname"),name);
        type(By.id("password"),password);
        type(By.id("password-confirm"),password);
        click(By.xpath("//button[@type='submit']"));
        if(!manager.isElementPresent(By.id("username"))){
            throw new IllegalArgumentException("Данные аккаунта не обновлены");
        }
    }



}

