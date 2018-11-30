package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactPhoneTests extends TestBase{
    @Test(enabled = false)
    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromeditForm = app.contact().infoFromEditForm(contact);

    }
}
