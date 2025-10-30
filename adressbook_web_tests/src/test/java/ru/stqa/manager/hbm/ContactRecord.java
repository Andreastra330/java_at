package ru.stqa.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Random;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middlename")
    public String middlename = "";

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "nickname")
    public String nickname = "";

    @Column(name = "title")
    public String title = "";

    @Column(name = "company")
    public String company = "";

    @Column(name = "address")
    public String address;

    @Column(name = "home")
    public String home;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "work")
    public String work;

    @Column(name = "fax")
    public String fax = "";

    @Column(name = "email")
    public String email;

    @Column(name = "email2")
    public String email2;

    @Column(name = "email3")
    public String email3;

    @Column(name = "homepage")
    public String homePage = "";

    @Column(name = "byear")
    public String birthdayYear = "";

    @Column(name = "ayear")
    public String anniversaryYear = "";

    @Column(name = "bday")
    public int birthDay = new Random().nextInt(31);

    @Column(name = "bmonth")
    public String birthMonth = "";

    @Column(name = "aday")
    public int anniversaryDay = new Random().nextInt(31);

    @Column(name = "amonth")
    public String anniversaryMonth = "";

    @Column(name = "photo")
    public String photo = "";

    public ContactRecord(int id, String firstname, String lastname, String address, String email, String email2, String email3, String home, String mobile, String work) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
    }

    public ContactRecord() {
    }

}
