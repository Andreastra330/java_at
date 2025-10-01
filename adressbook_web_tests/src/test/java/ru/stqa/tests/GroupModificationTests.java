package ru.stqa.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modify group name"));
    }
}
