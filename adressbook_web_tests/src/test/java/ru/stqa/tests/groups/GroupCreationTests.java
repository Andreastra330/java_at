package ru.stqa.tests.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;
import ru.stqa.utils.Utils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();

//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("", "group footer")) {
//                    result.add(new GroupData()
//                            .withName(name)
//                            .withHeader(header)
//                            .withFooter(footer));
//                }
//            }
//        }
//        var json = "";
//        try(var reader = new FileReader("groups.json");
//            var breader = new BufferedReader(reader);
//        ){
//            var line = breader.readLine();
//            while (line != null)
//            {
//                json = json + line;
//                line = breader.readLine();
//            }
//        }
       var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});
        result.addAll(value);
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