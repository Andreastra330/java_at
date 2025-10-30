package ru.stqa.model;

public record ContactData(
        String id, String firstName, String middleName, String lastName, String nickName, //Блок контактной информации
        String title, String company, String address, //Блок компании
        String home, String mobile, String work, String fax, //Блок телефонов
        String email, String email2, String email3, //Блок E-mail
        String homePage, String birthdayYear, String anniversaryYear,
        String birthDay, String birthMonth, String anniversaryDay, String anniversaryMonth, String photo) {

    public ContactData() {
        this("", "", "", "", "", "",
                "", "", "", "", "", "", "",
                "", "", "", "", "",
                "", "", "", "", "");
    }

    public ContactData(String id, String firstName, String lastName,
                       String address, String email, String email2, String email3,
                       String home, String mobile, String work) {
        this(id, firstName, "", lastName, "", "", "", address,
                home, mobile, work, "", email, email2, email3,
                "", "", "", "", "", "", "", "");
    }


    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withFirstName(String name) {
        return new ContactData(this.id, name, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withLastName(String name) {
        return new ContactData(this.id, this.firstName, this.middleName, name, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withWorkPhone(String phone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, phone, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth, photo);
    }

}