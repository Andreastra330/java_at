package ru.stqa.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }


}