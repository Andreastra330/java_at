package ru.stqa.tests.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;
import ru.stqa.utils.Utils;

import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {


    @Test
    void canModifyGroupHibernate() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupListHibernate();
        int index = new Random().nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName(Utils.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupListHibernate();
        oldGroups.set(index, testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(
                Set.copyOf(newGroups),
                Set.copyOf(oldGroups));

    }
}
