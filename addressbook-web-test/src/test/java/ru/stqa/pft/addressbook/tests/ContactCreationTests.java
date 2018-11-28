package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoAddNewPage();
        ContactData contact = new ContactData("firstName",
                "middleName", "lastName", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Comparator<? super ContactData> byLastName = (Comparator<ContactData>) (o1, o2) -> o1.getLastName().compareTo(o2.getLastName());
        before.add(contact);
        before.sort(byLastName);
        after.sort(byLastName);

        Assert.assertEquals(before,after);


    }
}
