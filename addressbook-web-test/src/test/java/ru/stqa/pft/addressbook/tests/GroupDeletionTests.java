package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("A"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Set<GroupData> before = app.group().All();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().All();
        Assert.assertEquals(before.size()-1, after.size());
        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }

}
