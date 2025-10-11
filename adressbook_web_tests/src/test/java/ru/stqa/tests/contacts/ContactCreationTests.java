package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "Andrey")) {
            for (var lastName : List.of("", "Orlov")) {
                for (var address : List.of("", "Moscow, Red Square, 1")) {
                    result.add(new ContactData("", firstName, lastName, address,
                            "+7-495-987-6543",
                            "my@work.com"
                    ));
                }
            }
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        ContactData createdContact = Collections.max(newContacts, app.contacts().compareById()); //Присваиваю новому объекту данные из newContacts у которого максимальный айди
        oldContacts.add(contact //В старые контакты добавляю новый с максимальным айди который присвоили раньше
                .withId(createdContact.id())
                .withFirstName(createdContact.firstName())
                .withLastName(createdContact.lastName())
                .withAdress(createdContact.address())
                .withEmail(createdContact.email())
                .withWorkPhone(createdContact.work())
        );

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

}
