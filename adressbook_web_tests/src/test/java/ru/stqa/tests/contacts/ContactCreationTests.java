package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.ContactData;
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

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "Andrey")) {
//            for (var lastName : List.of("", "Orlov")) {
//                for (var address : List.of("", "Moscow, Red Square, 1")) {
//                    result.add(new ContactData("", firstName, "Andreevich", lastName, "nickname",  "QA Engineer", "SberBuisness", address,
//                            "+7-495-123-4567", "+7-916-765-4321", "+7-495-987-6543", "+7-495-111-2222",
//                            "my@work.com", "my@personal.com", "my@backup.com",
//                            "my-site.com", "1997", "2015","31","September","24","June", ""));
//                }
//            }
//        }
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        ContactData createdContact = Collections.max(newContacts, app.contacts().compareById()); //Присваиваю новому объекту данные из newContacts у которого максимальный айди
        oldContacts.add(createdContact);

        Assertions.assertEquals( //Сравнение через стримы по айди с помощью компаратора, и дальнейшим преобразованием в списки
                oldContacts.stream().sorted(app.contacts().compareById()).toList(),
                newContacts.stream().sorted(app.contacts().compareById()).toList()
        );
    }

    @Test
    public void canCreateEmptyGroup() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateOnlyWithFirstName() {
        app.contacts().createContact(new ContactData().withFirstName("Andrey"));
    }

    @Test
    public void canCreateOnlyWithPhono(){
        app.contacts().createContact(new ContactData().withPhoto(Utils.randomFile("src/test/resources/images")));
    }
}
