package ru.stqa.manager;

import org.openqa.selenium.By;
import ru.stqa.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        deleteGroup();
        returnToGroupPage();
    }


    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modifyGroup(GroupData modifyGroup) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifyGroup);
        submitGroupModification();
        returnToGroupPage();
    }

    private void deleteGroup() {
        click(By.name("delete"));
    }


    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }


    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }


    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    private void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void fillGroupForm(GroupData group) {
        typeWithClear(By.name("group_name"), group.name());
        typeWithClear(By.name("group_header"), group.header());
        typeWithClear(By.name("group_footer"), group.footer());
    }

}
