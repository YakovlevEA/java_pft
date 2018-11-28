package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("AZ"));
        }
    }
    @Test
    public void testGroupModification(){

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");

        app.group().modify(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.group().all();
        //Assert.assertEquals(before.size()  , after.size());

        // before.remove(modifiedGroup);
        // before.add(group);
        //Assert.assertEquals(before,after);
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
