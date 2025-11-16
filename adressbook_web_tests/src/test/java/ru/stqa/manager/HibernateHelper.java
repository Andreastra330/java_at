package ru.stqa.manager;

import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.manager.hbm.ContactRecord;
import ru.stqa.manager.hbm.GroupRecord;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HibernateHelper extends HelperBase {
    private final SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        this.sessionFactory =
                new Configuration()
                        .addAnnotatedClass(GroupRecord.class)
                        .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convertGroupRecordToGroupData).collect(Collectors.toList());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convertContactRecordToContactData).collect(Collectors.toList());
    }

    private static GroupData convertGroupRecordToGroupData(GroupRecord record) {
        return new GroupData(Integer.toString(record.id), record.name, record.header, record.footer);
    }

    private static ContactData convertContactRecordToContactData(ContactRecord record) {
        return new ContactData(
                Integer.toString(record.id),
                record.firstname,
                record.middlename,
                record.lastname,
                record.nickname,
                record.title,
                record.company,
                record.address,
                record.home,
                record.mobile,
                record.work,
                record.fax,
                record.email,
                record.email2,
                record.email3,
                record.homePage,
                record.birthdayYear,
                record.anniversaryYear,
                Integer.toString(record.birthDay),
                record.birthMonth,
                Integer.toString(record.anniversaryDay),
                record.anniversaryMonth,
                record.photo);
    }

    private static GroupRecord convertGroupDataToGroupRecord(GroupData data) {
        var id = data.id();
        if (id.isEmpty()) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convertContactDataToContactRecord(ContactData data) {
        var id = data.id();
        if (id.isEmpty()) {
            id = "0";
        }
        return new ContactRecord(
                Integer.parseInt(id),
                data.firstName(),
                data.lastName(),
                data.address(),
                data.email(),
                data.email2(),
                data.email3(),
                data.home(),
                data.mobile(),
                data.work());
    }

    public List<GroupData> getGroupListHibernate() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }
    @Step("Получение списка контактов через Hibernate")
    public List<ContactData> getContactListHibernate() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroupDataToGroupRecord(groupData));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactDataToContactRecord(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
        return convertContactList(session.find(GroupRecord.class,group.id()).contacts);
        });
    }
}


