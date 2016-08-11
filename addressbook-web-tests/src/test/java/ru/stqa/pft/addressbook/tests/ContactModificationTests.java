package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Edward on 05.08.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactDataForm(new ContactData("Ed","Sidor","yaro","911","email.ru",null),false);
        app.getContactHelper().updateContact();
    }
}

