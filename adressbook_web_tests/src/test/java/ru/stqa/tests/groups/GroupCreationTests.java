package ru.stqa.tests.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();

        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
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
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
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
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}