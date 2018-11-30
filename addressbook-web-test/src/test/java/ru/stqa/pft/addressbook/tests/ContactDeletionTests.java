package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (!app.contact().isThereAContact(By.cssSelector(("[name='selected[]']")))) {
            app.goTo().addNew();
            app.contact().createContact(new ContactData());
        }
    }

    @Test (enabled = true)
    public void testContactDeletion(){

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().gotoHomePage();
        assertThat(before.size() - 1, equalTo(app.contact().count()));
        Contacts after = app.contact().all();
        assertThat(before.without(deletedContact), equalTo(after));

    }
}
