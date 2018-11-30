package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @Test
    public void testContactAddress(){

        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEdit = app.contact().infoFromEditForm(contact);
        assertThat(clearedFromSpaces(contact.getAddress()), equalTo(clearedFromSpaces(contactFromEdit.getAddress())));

    }

    public static String clearedFromSpaces(String address){
        return address.replaceAll("[\\s]","");
    }
}
