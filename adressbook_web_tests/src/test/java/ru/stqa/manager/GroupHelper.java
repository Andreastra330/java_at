package ru.stqa.manager;

import org.openqa.selenium.By;
import ru.stqa.config.ConfigReader;
import ru.stqa.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        deleteGroups();
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

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        deleteGroups();

    }

    private void deleteGroups() {
        click(By.name("delete"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void openGroupPage() {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(groupUrl())) {
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

    public int getCount() {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(groupUrl())) {
            openGroupPage();
        }
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public String groupUrl() {
        return ConfigReader.buildUrl("/group.php");
    }
}
