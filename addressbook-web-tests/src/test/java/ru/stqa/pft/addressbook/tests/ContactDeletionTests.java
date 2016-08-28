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
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
        }
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com","Test1"));
        }
    }

    @Test (enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);


    }

}
