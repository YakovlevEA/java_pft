package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        Groups after = app.group().all();
        //Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(app.group().Count(), equalTo(before.size() + 1));
        /*int max = 0;
        for (GroupData g : after){
            if (g.getId() > max){
                max = g.getId();
            }
        }*/

       /* Comparator<? super GroupData> byId = new Comparator<GroupData>() {
            @Override
            public int compare(GroupData o1, GroupData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        int max = after.stream().max(byId).get().getId();*/

        /*Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        int max = after.stream().max(byId).get().getId();
        group.setId(max);*/

        //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        /*before.add(group);
        Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(),g2.getId()) ;
        before.sort(byID);
        after.sort(byID);*/
        //group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        //before.add(group);
        //Assert.assertEquals(before, after);
        assertThat(after,
                equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

    @Test
    public void testGroupBadCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1'");
        app.group().create(group);
        assertThat(app.group().Count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }

}
