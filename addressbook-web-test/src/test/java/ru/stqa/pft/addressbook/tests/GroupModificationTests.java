package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("AZ"));
        }
    }
    @Test
    public void testGroupModification(){

        Set<GroupData> before = app.group().All();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");

        app.group().modify(group);
        Set<GroupData> after = app.group().All();
        Assert.assertEquals(before.size()  , after.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before,after);
    }

}
