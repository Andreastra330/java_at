package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;

import java.util.Collections;
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

    @Test
    void addContactInGroup()
    {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupListHibernate().get(0);

        var oldRelated = app.hbm().getContactInGroup(group);
        app.contacts().addContactInGroup(group);
        var newRelated = app.hbm().getContactInGroup(group);
        ContactData createdContact = Collections.max(newRelated, app.contacts().compareById());
        oldRelated.add(createdContact);
        Assertions.assertEquals(
                app.contacts().compareContactsWithModifyData(oldRelated),
                app.contacts().compareContactsWithModifyData(newRelated));
    }


    @Test
    void deleteContactInGroup()
    {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupListHibernate().get(0);

        if(app.contacts().checkContactsInGroup(group)){
            app.contacts().addContactInGroup(group);
        }
        var oldRelated = app.hbm().getContactInGroup(group);
        app.contacts().deleteContactFromGroup(group);
        var newRelated = app.hbm().getContactInGroup(group);

        Assertions.assertEquals(
                oldRelated.size()-1,
                newRelated.size()
        );

    }
}
