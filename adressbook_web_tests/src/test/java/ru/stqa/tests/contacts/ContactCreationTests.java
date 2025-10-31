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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        try (var inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("GeneratorFiles/contacts.json")) {

            if (inputStream == null) {
                throw new FileNotFoundException("Файл найден в GeneratorFiles");
            }
            ObjectMapper mapper = new ObjectMapper();
            var value = mapper.readValue(inputStream, new TypeReference<List<ContactData>>() {
            });
            result.addAll(value);
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContactsWithHibernate(ContactData contact) {
        var oldContacts = app.hbm().getContactListHibernate();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactListHibernate();
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
    public void canCreateOnlyWithPhoto() {
        app.contacts().createContact(new ContactData().withPhoto(Utils.randomFile("src/test/resources/images")));
    }

    @Test
    public void contactCreationGroup(){
        var contacts = new ContactData()
                .withFirstName(Utils.randomString(10))
                .withLastName(Utils.randomString(10))
                .withPhoto(Utils.randomFile("src/test/resources/images"));

        app.contacts().createContact(contacts);
    }

    @Test
    public void contactCreationGroupInGroup(){
        var contacts = new ContactData()
                .withFirstName(Utils.randomString(10))
                .withLastName(Utils.randomString(10))
                .withAddress(Utils.randomString(10))
                .withPhoto(Utils.randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupListHibernate().get(0);

        var oldRelated = app.hbm().getContactInGroup(group);
        app.contacts().createContact(contacts,group);
        var newRelated = app.hbm().getContactInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1,newRelated.size());
        ContactData createdContact = Collections.max(newRelated, app.contacts().compareById()); //Присваиваю новому объекту данные из newContacts у которого максимальный айди
        oldRelated.add(createdContact);
        Assertions.assertEquals(
                app.contacts().compareContactsWithModifyData(oldRelated),
                app.contacts().compareContactsWithModifyData(newRelated));

    }
}
