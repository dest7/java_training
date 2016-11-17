package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;


public class GroupCreationTests extends TestBase {

//    public Iterator<Object[]> validGroupsFromXml() throws IOException {
//        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
//            String xml = "";
//            String line = reader.readLine();
//            while (line != null){
//                xml += line;
//                line = reader.readLine();
//            }
//            XStream xstream = new XStream();
//            xstream.processAnnotations(GroupData.class);
//            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
//            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
//        }
//    }

//    @DataProvider //провайдер тестовых данных
//    public Iterator<Object[]> validGroupsFromJson() throws IOException {
//       try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
//           String json = "";
//           String line = reader.readLine();
//           while (line != null){
//               json += line;
//               line = reader.readLine();
//           }
//           Gson gson = new Gson();
//           List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); //List<GroupData>.class
//           return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
//       }
//    }

    @Test //(dataProvider = "validGroupsFromJson")
    public void testStatHDGraph() throws Exception {

        app.group().graph();
    }

//    @Test
//    public void testBadGroupCreation() {
//        app.goTo().groupPage();
//        Groups before = app.db().groups();
//        GroupData group = new GroupData().withName("Test'");
//        app.group().create(group);
//        assertThat(app.group().count(), equalTo(before.size()));
//        Groups after = app.db().groups();
//        assertThat(after, equalTo(before));
//    }
}
