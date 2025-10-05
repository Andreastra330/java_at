package ru.stqa.model;

public record ContactData(
        String firstName, String middleName, String lastName, String nickName, //Блок контактной информации
        String title, String company, String address, //Блок компании
        String home, String mobile, String work, String fax, //Блок телефонов
        String email, String email2, String email3, //Блок E-mail
        String homePage, String birthdayYear, String anniversaryYear,
        String birthDay, String birthMonth, String anniversaryDay, String anniversaryMonth) {

    public ContactData() {
        this("", "", "", "",
                "", "", "",
                "", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "", "");
    }

    public ContactData withFirstName(String name) {
        return new ContactData(name, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, this.birthMonth, this.anniversaryDay, this.anniversaryMonth);
    }

    public ContactData withBirthMonth(String birthMonth) {
        return new ContactData(this.firstName, this.middleName, this.lastName, this.nickName, this.title, this.company, this.address,
                this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homePage, this.birthdayYear,
                this.anniversaryYear, this.birthDay, birthMonth, this.anniversaryDay, this.anniversaryMonth);
    }
}