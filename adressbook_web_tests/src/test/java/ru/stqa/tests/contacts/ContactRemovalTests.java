package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.Random;

public class ContactRemovalTests extends TestBase {


    @Test
    public void canRemovalContactHibernate() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstName", "lastName", "address", "email",
                    "email2", "email3", "home", "mobile", "work"));
        }
        var oldContacts = app.hbm().getContactListHibernate();
        int index = new Random().nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactListHibernate();
        oldContacts.remove(index);
        Assertions.assertEquals(
                oldContacts.stream().sorted(app.contacts().compareById()).toList(),
                newContacts.stream().sorted(app.contacts().compareById()).toList()
        );
    }

    @Test
    public void canRemovalAllContactsWithClick() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteAllContactsWithClick();
    }

    @Test
    public void canRemovalAllContactsWithBtn() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteAllContacts();
    }
}
