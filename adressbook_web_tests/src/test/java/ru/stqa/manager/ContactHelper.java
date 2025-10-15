package ru.stqa.manager;

import org.openqa.selenium.By;
import ru.stqa.config.ConfigReader;
import ru.stqa.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        initialDelete();
        returnToContactPage();

    }

    public void modifyContact(ContactData contact, ContactData modifyContact)
    {
        openContactOnEdit(contact);
        fillForm(modifyContact);
        saveEditOnForm();
        returnToContactPage();
    }

    private void saveEditOnForm() {
        click(By.name("update"));
    }

    private void fillForm(ContactData contact) {
        typeWithClear(By.name("firstname"),contact.firstName());
        typeWithClear(By.name("lastname"),contact.lastName());
        typeWithClear(By.name("address"),contact.address());
        typeWithClear(By.name("work"),contact.work());
        typeWithClear(By.name("email"),contact.email());

    }

    private void openContactOnEdit(ContactData contact) {
        click(By.xpath(String.format("//a[@href= 'edit.php?id=%s']",contact.id())));
    }


    public void deleteAllContactsWithClick() {
        selectAllGroupsWithClick();
        initialDelete();
        returnToContactPage();
    }

    public void deleteAllContacts() {
        selectAllGroups();
        initialDelete();
        returnToContactPage();
    }

    public void initialDelete() {
        click(By.name("delete"));
    }

    public void selectContact(ContactData contact) {
        click(By.xpath(String.format("//td[@class = 'center']/input[@id = '%s']", contact.id())));
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
        fillInfo(contact);
    }


    private void fillInfo(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("work"), contact.work());
    }

    public int getCount() {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(ConfigReader.getBaseUrl())) {
            openContactPage();
        }
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllGroupsWithClick() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void selectAllGroups() {
        click(By.xpath("//input[@onclick='MassSelection()']"));
    }

    public List<ContactData> getList() {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(ConfigReader.getBaseUrl())) {
            openContactPage();
        }
        var contacts = new ArrayList<ContactData>();
        var checkboxes = manager.driver.findElements(By.xpath("//tr[@name = 'entry']"));
        for (var checkbox : checkboxes) {
            var id = checkbox.findElement(By.xpath("./td[1]//input[@name = 'selected[]']")).getAttribute("id");
            var lastName = checkbox.findElement(By.xpath("./td[2]")).getText();
            var firstName = checkbox.findElement(By.xpath("./td[3]")).getText();
            var address = checkbox.findElement(By.xpath("./td[4]")).getText();
            var allEmails = checkbox.findElement(By.xpath("./td[5]")).getText();
            var allPhone = checkbox.findElement(By.xpath("./td[6]")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAdress(address));
        }
        return contacts;
    }

    public Comparator<ContactData> compareById() {
        return (o1, o2) -> Integer.compare(
                Integer.parseInt(o1.id()),
                Integer.parseInt(o2.id())
        );
    }
}
