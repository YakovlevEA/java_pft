package ru.stqa.pft.addressbook.appmanager.generators;

import ru.stqa.pft.addressbook.appmanager.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;

import java.io.File;
import java.util.List;

public class CroupDataGenerator {
    public static void main (String[] arg){
        int count = Integer.parseInt(arg[0]);
        File file = new File(arg[1]);
        List<GroupData> groups = generateGroup(count);
                save(groups, file);
    }

    private static List<GroupData> generateGroup(int count) {

    }
}
