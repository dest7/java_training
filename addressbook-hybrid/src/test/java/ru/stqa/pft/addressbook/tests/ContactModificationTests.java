package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Edward on 05.08.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions(){
        if(app.db().contacts().size() == 0){

            app.contact().createContact(new ContactData()
                    .withFirstName("Ed")
                    .withLastName("JavaTest")
                    .withAddress("Moscow City")
                    .withHomePhone("+7(742)-1231231")
                    .withMobilePhone("33-33-33")
                    .withWorkPhone("22 22 22")
                    .withEmail("adressbook@abmail.com")
                    .withEmailTwo("adressbook2@abmail.com")
                    .withEmailThree("adressbook3@abmail.com"));
                    //.withGroup("Test1"));
            app.goTo().homePage();
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Ivanov")
                .withLastName("Sidor")
                .withAddress("Moscow")
                .withHomePhone("+7(742)-6666666")
                .withMobilePhone("44-44-44")
                .withWorkPhone("55 55 55")
                .withEmail("adressbook@abmail.ru")
                .withEmailTwo("adressbook2@abmail.ru")
                .withEmailThree("adressbook3@abmail.ru");
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }

}

