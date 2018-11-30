package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoHomePage();
        if (! app.contact().isThereAContact(By.cssSelector(("[name='selected[]']")))){
            app.goTo().addNew();
            app.contact().createContact(new ContactData());
        }
    }
    @Test (enabled = true)
    public void testContactModification(){

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("ModFirstName").withMiddleName("ModMiddleName").withLastName("ModLastName");
        app.contact().modify(contact);

        assertThat(before.size(), equalTo(app.contact().count()));
        Contacts after = app.contact().all();
        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));

    }
}
