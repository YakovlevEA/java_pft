package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact(By.cssSelector(("[name='selected[]']")))){
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("","","","null"));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactform(new ContactData("ModFirstName",
                "ModMiddleName","ModLastName", "null"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
