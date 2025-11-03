package ru.stqa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.config.ConfigReader;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;

import java.util.*;
import java.util.stream.Collectors;

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

    public void createContact(ContactData contact, GroupData group) {
        openContactCreationPage();
        fillContactForm(contact);
        selectGroup(group);
        saveContactForm();
        returnToContactPage();
    }

    public void deleteContactFromGroup(GroupData group) {
        openContactPage();
        choiceGroup(group);
        removeFromGroup();
        openContactPage();
    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }


    public boolean checkContactsInGroup(GroupData group) {
        openContactPage();
        new Select(manager.findEl(By.name("group"), 5)).selectByValue(group.id());
        return manager.driver.findElements(By.name("selected[]")).isEmpty();
    }

    private void choiceGroup(GroupData group) {
        new Select(manager.findEl(By.name("group"), 5)).selectByValue(group.id());
        click(By.name("selected[]"));
    }

    public void addContactInGroup(GroupData group) {
        openContactPage();
        choiceContactWithoutGroup();
        contactToGroup(group);
        openContactPage();
    }

    private void contactToGroup(GroupData group) {
        new Select(manager.findEl(By.name("to_group"), 5)).selectByValue(group.id());
        click(By.xpath("//input[@value='Add to']"));
    }

    private void choiceContactWithoutGroup() {
        new Select(manager.findEl(By.name("group"), 5)).selectByValue("[none]");
        if (manager.driver.findElements(By.name("selected[]")).isEmpty()) {
            manager.hbm().createContact(new ContactData("", "firstName", "lastName", "address", "email",
                    "email2", "email3", "home", "mobile", "work"));
            new Select(manager.findEl(By.name("group"), 5)).selectByValue("[none]");
        }
        click(By.name("selected[]"));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.findEl(By.name("new_group"), 5)).selectByValue(group.id());
    }

    public void deleteContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        initialDelete();
        returnToContactPage();

    }

    public void modifyContact(ContactData contact, ContactData modifyContact) {
        openContactPage();
        openContactOnEdit(contact);
        fillForm(modifyContact);
        saveEditOnForm();
        returnToContactPage();
    }

    public void modifyContactHibarnate(ContactData contact, ContactData modifyContact) {
        openContactPage();
        openContactOnEdit(contact);
        fillForm(modifyContact);
        saveEditOnForm();
        returnToContactPage();
    }

    private void saveEditOnForm() {
        click(By.name("update"));
    }

    private void fillForm(ContactData contact) {
        typeWithClear(By.name("firstname"), contact.firstName());
        typeWithClear(By.name("lastname"), contact.lastName());
        typeWithClear(By.name("address"), contact.address());
        typeWithClear(By.name("work"), contact.work());
        typeWithClear(By.name("email"), contact.email());

    }

    private void openContactOnEdit(ContactData contact) {
        var currentUrl = manager.driver.getCurrentUrl();
        assert currentUrl != null;
        if (!currentUrl.equals(ConfigReader.getBaseUrl())) {
            openContactPage();
        }
        click(By.xpath(String.format("//a[@href= 'edit.php?id=%s']", contact.id())));
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
        clickIfClickable(By.linkText("home"));
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
        attach(By.name("photo"), contact.photo());
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
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address));
        }
        return contacts;
    }

    public Comparator<ContactData> compareById() {
        return (o1, o2) -> Integer.compare(
                Integer.parseInt(o1.id()),
                Integer.parseInt(o2.id())
        );
    }

    public List<String> compareContactsWithModifyData(List<ContactData> contact) {
        return contact.stream()
                .sorted(compareById())
                .map(g -> g.id() + ":" + g.firstName() + ":" + g.lastName() + ":" + g.address())
                .toList();
    }


    public String getPhones(ContactData contact) {
        return manager.findEl(By.xpath(String.format("//input[@id = '%s']/../../td[6]", contact.id())), 5).getText();

    }

    public Map<String, String> getPhonesWithMap() {
        var result = new HashMap<String, String>();
        var rows = manager.findElements(By.name("entry"), 5);
        rows.forEach(webElement -> {
            var id = webElement.findElement(By.tagName("input")).getAttribute("id");
            var phones = webElement.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        });

        return result;
    }


    public Map<String, String> getPhonesWithMapStream() {
        return manager.findElements(By.name("entry"), 5).stream()
                .collect(Collectors.toMap(
                        e -> e.findElement(By.tagName("input")).getAttribute("id"),
                        e -> e.findElements(By.tagName("td")).get(5).getText()
                ));
    }

    public String getEmails(ContactData contact) {
        return manager.findEl(By.xpath(String.format("//input[@id = '%s']/../../td[5]", contact.id())), 5).getText();
    }

    public Map<String, String> getEmailsWithMapStream() {
        return manager.findElements(By.name("entry"), 5).stream()
                .collect(Collectors.toMap(
                        e -> e.findElement(By.tagName("input")).getAttribute("id"),
                        e -> e.findElements(By.tagName("td")).get(4).getText()
                ));
    }

    public String getAddress(ContactData contact) {
        return manager.findEl(By.xpath(String.format("//input[@id = '%s']/../../td[4]", contact.id())), 5).getText();
    }

    public Map<String, String> getAddressWithMapStream() {
        return manager.findElements(By.name("entry"), 5).stream()
                .collect(Collectors.toMap(
                        e -> e.findElement(By.tagName("input")).getAttribute("id"),
                        e -> e.findElements(By.tagName("td")).get(3).getText()
                ));
    }
}
