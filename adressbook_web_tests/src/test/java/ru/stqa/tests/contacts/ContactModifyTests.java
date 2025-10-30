package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.Random;

public class ContactModifyTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData());
        }

        var oldContacts = app.contacts().getList();
        int index = new Random().nextInt(oldContacts.size());
        var modifyData = new ContactData()
                .withFirstName("MODIFY NAME")
                .withLastName("MODIFY LAST NAME")
                .withAddress("MODIFY ADDRESS");

        app.contacts().modifyContact(oldContacts.get(index), modifyData);
        var newContacts = app.contacts().getList();
        oldContacts.set(index, modifyData.withId(oldContacts.get(index).id()));

        Assertions.assertEquals(
                oldContacts.stream().sorted(app.contacts().compareById()).toList(),
                newContacts.stream().sorted(app.contacts().compareById()).toList()
        );
    }

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
