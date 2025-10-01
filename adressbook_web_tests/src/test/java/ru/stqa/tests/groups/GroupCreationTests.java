package ru.stqa.tests.groups;

import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithOnlyName() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}