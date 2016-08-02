package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        addNewContact();
        fillContactDataForm(new ContactData("Ed", "JavaTest", "Moscow City", "8-495-1231231", "adressbook@abmail.com"));
        enterNewContactInAb();
        returnToHomePage();
    }


}
