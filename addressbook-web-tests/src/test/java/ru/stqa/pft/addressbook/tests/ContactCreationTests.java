package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


    @DataProvider //провайдер тестовых данных
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));

        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //List<ContactData>.class
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }



    @Test (dataProvider = "validContactsFromJson")//(enabled = false)
    public void testContactCreationUseJson(ContactData contact) {

//        app.goTo().groupPage();
//        if (! app.group().isThereAGroup()){
//            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
//        }
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().createContact(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


    @Test //(enabled = false)
    public void testContactCreation (){

        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
        }
        app.goTo().homePage();
        File photo = new File("src/test/resources/test.png");
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Ed")
                .withLastName("JavaTest")
                .withAddress("Moscow City")
                .withHomePhone("+7(742)-1231231")
                .withMobilePhone("33-33-33")
                .withWorkPhone("22 22 22")
                .withEmail("adressbook@abmail.com")
                .withEmailTwo("adressbook2@abmail.com")
                .withEmailThree("adressbook3@abmail.com")
                .withGroup("Test1")
                .withPhoto(photo);
        app.contact().createContact(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
   /* @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/test.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
