package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Edward on 12.09.2016.
 */
public class ContactPostAddressTests extends TestBase{
    @BeforeMethod

    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed").withLastName("JavaTest").withAddress("Moscow City")
                    .withHomePhone("8-495-1231231").withEmail("adressbook@abmail.com").withGroup("Test1"));
        }
}}
//
//@Test (enabled = false)
//
//public void postAddressTest(){
//    app.goTo().homePage();
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
//}

