package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemovalContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteContact();
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
