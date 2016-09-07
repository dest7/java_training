package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{


    @BeforeMethod

    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
        }
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed").withLastName("JavaTest").withAddress("Moscow City")
                    .withPhone("8-495-1231231").withEmail("adressbook@abmail.com").withGroup("Test1"));
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withOut(deleteContact)));
    }

}
