package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

import java.util.Random;

public class ContactModifyTests extends TestBase {

    @Test
    void canModifyContact() {
        if(app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData("","2","3","4","5","6"));
        }

        var oldContacts = app.contacts().getList();
        int index = new Random().nextInt(oldContacts.size());
        var modifyData = new ContactData()
                .withFirstName("MODIFY NAME")
                .withLastName("MODIFY LAST NAME")
                .withAdress("MODIFY ADDRESS")
                .withEmail("MODIFY EMAIL")
                .withWorkPhone("+111111111");

        app.contacts().modifyContact(oldContacts.get(index), modifyData);
        var newContacts = app.contacts().getList();
        oldContacts.set(index, modifyData.withId(oldContacts.get(index).id()));

        Assertions.assertEquals(
                oldContacts.stream().sorted(app.contacts().compareById()).toList(),
                newContacts.stream().sorted(app.contacts().compareById()).toList()
        );

    }
}
