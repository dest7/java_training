package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Edward on 05.08.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
        }
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ed", "JavaTest", null, null, null,null));
            //app.getContactHelper().createContact(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com","Test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"sidor","Ivanov",null,null,null,null);
        app.getContactHelper().fillContactDataForm(contact,false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}

