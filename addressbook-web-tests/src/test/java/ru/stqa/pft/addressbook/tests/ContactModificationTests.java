package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Edward on 05.08.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed").withLastName("JavaTest").withAddress("Moscow City")
                    .withPhone("8-495-1231231").withEmail("adressbook@abmail.com").withGroup("Test1"));
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        //ContactData contact = new ContactData(before.get(index).getId(),"sidor","Ivanov",null,null,null,null);
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstName("Sidor").withLastName("Ivanov");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (con1, con2) -> Integer.compare(con1.getId(),con2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}

