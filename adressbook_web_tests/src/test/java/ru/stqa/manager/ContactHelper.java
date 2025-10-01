package ru.stqa.manager;

import org.openqa.selenium.By;
import ru.stqa.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactCreationPage();
        fillContactForm(contact);
        saveContactForm();
        returnToContactPage();
    }

    public void deleteContact() {
        selectContact();
        initialDelete();
        returnToContactPage();

    }

    public void initialDelete() {
        click(By.name("delete"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));

    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void saveContactForm() {
        click(By.name("submit"));
    }

    public void openContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void openContactPage() {
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contact) {
        fillContactInfo(contact);
        fillCompanyInfo(contact);
        fillPhoneInfo(contact);
        fillEmailInfo(contact);
        fillPageInfo(contact);
        fillDateForm(contact);
    }

    private void fillPageInfo(ContactData contact) {
        type(By.name("homepage"), contact.homePage());
        type(By.name("byear"), contact.birthdayYear());
        type(By.name("ayear"), contact.anniversaryYear());
    }

    private void fillDateForm(ContactData contact) {
        if (!contact.birthDay().isEmpty()) {
            choiceBirthDay(contact.birthDay());
        }
        if (!contact.birthMonth().isEmpty()) {
            choiceBirthdayMonth(contact.birthMonth());
        }
        if (!contact.anniversaryDay().isEmpty()) {
            choiceAnniversaryDay(contact.anniversaryDay());
        }
        if (!contact.anniversaryMonth().isEmpty()) {
            choiceAnniversaryMonth(contact.anniversaryMonth());
        }
    }


    private void choiceBirthdayMonth(String month) {
        click(By.name("bmonth"));
        click(By.xpath(String.format("//select[@name='bmonth']/option[@value='%s']", month)));
    }

    private void choiceBirthDay(String day) {
        click(By.name("bday"));
        manager.scroll(By.xpath(String.format("//select[@name='bday']/option[@value='%s']", day)));
        click(By.xpath(String.format("//select[@name='bday']/option[@value='%s']", day)));
    }

    private void choiceAnniversaryMonth(String month) {
        click(By.name("amonth"));
        click(By.xpath(String.format("//select[@name='amonth']/option[@value='%s']", month)));
    }

    private void choiceAnniversaryDay(String day) {
        click(By.name("aday"));
        manager.scroll(By.xpath(String.format("//select[@name='aday']/option[@value='%s']", day)));
        click(By.xpath(String.format("//select[@name='aday']/option[@value='%s']", day)));
    }

    private void fillEmailInfo(ContactData contact) {
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
    }

    private void fillPhoneInfo(ContactData contact) {
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
    }

    private void fillCompanyInfo(ContactData contact) {
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
    }

    private void fillContactInfo(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickName());
    }
}
