package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupform(new GroupData("test1", "test2", "test3"));
        submitGroupCreatin();
        returnToGroupPage();
    }

}
