package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.Random;

public class ContactModifyTests extends TestBase {

    @Test
    void canModifyContactHibernate() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData(
                    "", "firstName", "lastName", "address", "email",
                    "email2", "email3", "home", "mobile", "work"));
        }

        var oldContacts = app.hbm().getContactListHibernate();
        int index = new Random().nextInt(oldContacts.size());
        var modifyData = new ContactData()
                .withId(oldContacts.get(index).id())
                .withFirstName("MODIFY NAME")
                .withLastName("MODIFY LAST NAME")
                .withAddress("MODIFY ADDRESS");
        app.contacts().modifyContactHibarnate(oldContacts.get(index), modifyData);
        var newContacts = app.hbm().getContactListHibernate();
        oldContacts.set(index, modifyData);

        Assertions.assertEquals(
                app.contacts().compareContactsWithModifyData(oldContacts),
                app.contacts().compareContactsWithModifyData(newContacts)
        );

    }
}
