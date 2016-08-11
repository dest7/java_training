package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHome();
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactDataForm(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com","Test1"),true);
        app.getContactHelper().enterNewContactInAb();
        app.returnToHomePage();
    }


}
