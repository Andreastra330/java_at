package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;
import ru.stqa.tests.TestBase;
import org.junit.jupiter.api.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactModifyTests extends TestBase {

    @Test
    @Order(1)
    void canModifyContactHibernate() throws InterruptedException {
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
        Thread.sleep(2000);
        app.contacts().modifyContactHibarnate(oldContacts.get(index), modifyData);
        var newContacts = app.hbm().getContactListHibernate();
        oldContacts.set(index, modifyData);

        Assertions.assertEquals(
                app.contacts().compareContactsWithModifyData(oldContacts),
                app.contacts().compareContactsWithModifyData(newContacts)
        );
    }

    @Test
    @Order(2)
    void addContactInGroup()
    {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupListHibernate().get(0);

        var oldRelated = app.hbm().getContactInGroup(group);
        app.contacts().addContactInGroup(group);
        var newRelated = app.hbm().getContactInGroup(group);
        var extraGroups = newRelated.stream().filter(g -> !oldRelated.contains(g)).toList();
        oldRelated.addAll(extraGroups);
        Assertions.assertEquals(
                app.contacts().compareContactsWithModifyData(oldRelated),
                app.contacts().compareContactsWithModifyData(newRelated));
    }


    @Test
    @Order(3)
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
