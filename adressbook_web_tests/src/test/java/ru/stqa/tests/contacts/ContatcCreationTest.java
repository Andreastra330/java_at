package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Test;
import ru.stqa.model.ContactData;
import ru.stqa.tests.TestBase;

public class ContatcCreationTest extends TestBase {

    @Test
    public void canCreateGroup() {
        app.contacts().createContact(new ContactData("Andrey", "Andreevich", "Orlov", "Andreastra",
                "QA Engineer", "SberBuisness", "Moscow, Red Square, 1",
                "+7-495-123-4567", "+7-916-765-4321", "+7-495-987-6543", "+7-495-111-2222",
                "my@work.com", "my@personal.com", "my@backup.com",
                "my-site.com", "1997", "2015","31","September","24","June"));
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
