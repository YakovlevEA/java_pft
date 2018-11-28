package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact(By.cssSelector(("[name='selected[]']")))){
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("","","","null"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() + 1);
        ContactData contact = new ContactData("ModFirstName",
                "ModMiddleName","ModLastName", "null");
        app.getContactHelper().fillContactform(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Comparator<? super ContactData> ByLastNameFirstName = new Comparator<ContactData>() {
            @Override
            public int compare(ContactData o1, ContactData o2) {
                String o1LastNameFirstName = o1.getLastName() + o1.getFirstName();
                String o2LastNameFirstName = o2.getLastName() + o2.getFirstName();
                return o1LastNameFirstName.compareTo(o2LastNameFirstName);
            }
        };
        before.remove(before.size() - 1);
        before.add(contact);
        before.sort(ByLastNameFirstName);

        after.sort(ByLastNameFirstName);
        Assert.assertEquals(after, before);
    }
}
