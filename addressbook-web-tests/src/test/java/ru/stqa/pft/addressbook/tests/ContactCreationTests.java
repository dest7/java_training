package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
        }
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactDataForm(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com","Test1"),true);
        app.getContactHelper().enterNewContactInAb();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before +1);
    }


}
