package ru.stqa.tests.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;

import java.util.Random;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.groups().getList();
        int index = new Random().nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName("MODIFY");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getList();
        oldGroups.set(index, testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(
                newGroups.stream().sorted(app.groups().compareById()).toList(),
                oldGroups.stream().sorted(app.groups().compareById()).toList());
    }

    @Test
    void canModifyGroupHibernate() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupListHibernate();
        int index = new Random().nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName("MODIFY");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupListHibernate();
        oldGroups.set(index, testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(
                newGroups.stream().sorted(app.groups().compareById()).toList(),
                oldGroups.stream().sorted(app.groups().compareById()).toList());
    }
}
