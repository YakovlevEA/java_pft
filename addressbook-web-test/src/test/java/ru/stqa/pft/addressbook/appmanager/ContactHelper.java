package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactform(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContact(int id) {
        wd.findElement(By.id(id + "")).click();
    }

    public void deleteSelectionContact() {
        click(By.cssSelector("[value = 'Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModificationById(int id ) {

        click(By.xpath(String.format("//input[@id='%s']/../../td/a/img[@title='Edit']/..", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        fillContactform(new ContactData().
                        withFirstName(contactData.getFirstName()).
                        withLastName(contactData.getLastName()).
                        withGroup(contactData.getGroup()).
                        withPhoto(contactData.getPhoto()),
                        true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact(By locator) {
        return isElementPresent(locator);
    }

    public  int count (){
        List<WebElement> elements = wd.findElements(By.name("entry"));
        return elements.size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            ContactData contact = new ContactData().withFirstName(name).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
    public Contacts all(){
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement row: elements){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("id"));
            String firstName = cells.get(1).getText();
            String lastName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
                    withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectionContact();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactform(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName  = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");

        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return  new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

}
