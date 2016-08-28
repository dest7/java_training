package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


    @BeforeMethod

    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData("Test1", "Test2", "Test3"));
        }
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().createContact(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com","Test1"));
        }
    }

    @Test (enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);


    }


}
