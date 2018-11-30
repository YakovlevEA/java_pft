package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
    @Test(enabled = true)
    public void testContactPhones(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHomePhone(), equalTo(cleared(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleared(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleared(contactInfoFromEditForm.getWorkPhone())));
    }

    public String cleared (String phone){
        return phone.replaceAll("[-()\\s]","");
    }
}
