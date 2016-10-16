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
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
        }
        app.goTo().homePage();
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
                    .withEmailThree("adressbook3@abmail.com")
                    .withGroup("Test1"));
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withOut(deleteContact)));
    }

}
