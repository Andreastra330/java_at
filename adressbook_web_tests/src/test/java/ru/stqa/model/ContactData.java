package ru.stqa.model;

public record ContactData(
        String id, String firstName, String lastName,  //Блок контактной информации
        String address, //Блок компании
        String work,  //Блок телефонов
        String email   //Блок E-mail
) {

    public ContactData() {
        this("", "", "",
                "",
                "",
                ""
        );
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.work, this.email);
    }

    public ContactData withFirstName(String name) {
        return new ContactData(this.id, name, this.lastName, this.address, this.work, this.email);
    }

    public ContactData withLastName(String name) {
        return new ContactData(this.id, this.firstName, name, this.address, this.work, this.email);
    }

    public ContactData withAdress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.work, this.email);
    }

    public ContactData withWorkPhone(String phone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, phone, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.work, email);
    }

}