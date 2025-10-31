package ru.stqa.tests.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;
import ru.stqa.utils.Utils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .withName(Utils.randomString(10))
                .withHeader(Utils.randomString(20))
                .withFooter(Utils.randomString(30)));
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData()
                        .withName("group name'")
                        .withHeader("")
                        .withFooter("")
        ));
        return result;
    }


    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroupWithJdbc(GroupData group) {
        var oldGroups = app.jdbc().getGroupListJdbc();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupListJdbc();
        GroupData createdGroup = Collections.max(newGroups, app.groups().compareById()); //Забираю максимальный айди через компаратор
        oldGroups.add(group //в старую группу добавляю реальный объект созданный раньше
                .withId(createdGroup.id())
                .withName(createdGroup.name())
                .withHeader(createdGroup.header())
                .withFooter(createdGroup.footer())
        );
        Assertions.assertEquals( //через стримы сравниваю между собой списки и превращаю обратно в списки
                oldGroups.stream().sorted(app.groups().compareById()).toList(),
                newGroups.stream().sorted(app.groups().compareById()).toList());
    }


    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroupWithHibernate(GroupData group) {
        var oldGroups = app.hbm().getGroupListHibernate();
        app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        var newGroups = app.hbm().getGroupListHibernate();
        GroupData createdGroup = Collections.max(newGroups, app.groups().compareById()); //Забираю максимальный айди через компаратор
        oldGroups.add(group //в старую группу добавляю реальный объект созданный раньше
                .withId(createdGroup.id())
                .withName(createdGroup.name())
                .withHeader(createdGroup.header())
                .withFooter(createdGroup.footer())
        );
        Assertions.assertEquals( //через стримы сравниваю между собой списки и превращаю обратно в списки
                oldGroups.stream().sorted(app.groups().compareById()).toList(),
                newGroups.stream().sorted(app.groups().compareById()).toList());
    }

    @Test
    public void checkUIandDB() {
        var dbGroups = app.jdbc().getGroupListJdbc();
        var uiGroups = app.groups().getList();
        Assertions.assertEquals(
                app.groups().compareGroupsWithStream(dbGroups),
                app.groups().compareGroupsWithStream(uiGroups)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}