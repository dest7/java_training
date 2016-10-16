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
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed")
                    .withLastName("JavaTest")
                    .withAddress("Moscow City")
                    .withHomePhone("8-495-1231231")
                    .withEmail("adressbook@abmail.com")
                    .withGroup("Test1"));
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("Sidor")
                .withLastName("Ivanov")
                .withAddress("Moscow")
                .withHomePhone("8-495-7777777")
                .withEmail("adressbook@abmail.ru");
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }

}

