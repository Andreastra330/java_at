package ru.stqa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.stqa.config.ConfigReader;
import ru.stqa.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public void removeGroup(GroupData group) {
        openGroupPage();
        selectGroup(group);
        deleteGroups();

    }

    public void modifyGroup(GroupData group, GroupData modifyGroup) {
        openGroupPage();
        selectGroup(group);
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
        try {
            click(By.linkText("group page"));
        } catch (TimeoutException e) {
            click(By.linkText("groups"));
        }
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {
        click(By.xpath(String.format(("//input[@value='%s']"), group.id())));
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

    public List<GroupData> getList() {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(groupUrl())) {
            openGroupPage();
        }
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.xpath("//span[@class='group']"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public Comparator<GroupData> compareById() {
        return (o1, o2) -> Integer.compare(
                Integer.parseInt(o1.id()),
                Integer.parseInt(o2.id())
        );
    }


}
