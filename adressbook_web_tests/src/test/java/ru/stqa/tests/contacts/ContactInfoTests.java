package ru.stqa.tests.contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactListHibernate();
        //var contact = contacts.get(new Random().nextInt(contacts.size()));
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(
                        contact.home(), contact.mobile(), contact.work())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testPhonesAll() {
        var contacts = app.hbm().getContactListHibernate();
        var expected = contacts.stream().collect(Collectors.toMap(
                contact -> contact.id(),
                contact -> Stream.of(
                                contact.home(), contact.mobile(), contact.work())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhonesWithMapStream();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
        var contacts = app.hbm().getContactListHibernate();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(
                        contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testEmailsAll() {
        var contacts = app.hbm().getContactListHibernate();
        var expected = contacts.stream().collect(Collectors.toMap(
                contact -> contact.id(),
                contact -> Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmailsWithMapStream();
        Assertions.assertEquals(expected, emails);

    }

    @Test
    void testAddress() {
        var contacts = app.hbm().getContactListHibernate();
        var contact = contacts.get(5);
        var address = app.contacts().getAddress(contact);
        var expected = Stream.of(
                        contact.address())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected, address);
    }

    @Test
    void testAddressAll() {
        var contacts = app.hbm().getContactListHibernate();
        var expected = contacts.stream().collect(Collectors.toMap(
                contact -> contact.id(),
                contact -> Stream.of(contact.address())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var addresses = app.contacts().getAddressWithMapStream();
        Assertions.assertEquals(expected, addresses);

    }
}
