package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test (enabled = false)
    public void testContactDeletion(){
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact(By.cssSelector(("[name='selected[]']")))){
            app.goTo().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("","","","null"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectionContact();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);

        Assert.assertEquals(before,after);

    }
}
