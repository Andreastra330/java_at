package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "Andrey")) {
            for (var middleName : List.of("", "Andreevich")) {
                for (var lastName : List.of("", "Orlov")) {
                    for (var nickName : List.of("", "Andreastra")) {
                                    result.add(new ContactData(firstName, middleName, lastName, nickName,  "QA Engineer", "SberBuisness", "Moscow, Red Square, 1",
                                            "+7-495-123-4567", "+7-916-765-4321", "+7-495-987-6543", "+7-495-111-2222",
                                            "my@work.com", "my@personal.com", "my@backup.com",
                                            "my-site.com", "1997", "2015","31","September","24","June"));
                                }
                            }
                        }
                    }



        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        app.contacts().createContact(contact);
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
    public void canCreateOnlyWithBirthMonth() {
        app.contacts().createContact(new ContactData().withBirthMonth("September"));
    }
}
