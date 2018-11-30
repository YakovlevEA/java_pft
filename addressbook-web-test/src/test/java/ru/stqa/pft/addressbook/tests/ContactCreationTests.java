package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test(enabled = true)
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        ContactData contact = new ContactData().withFirstName("NewName").withLastName("NewLastName").withGroup("test1");
        app.contact().createContact(contact);
        assertThat(app.contact().count() ,equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }
}
