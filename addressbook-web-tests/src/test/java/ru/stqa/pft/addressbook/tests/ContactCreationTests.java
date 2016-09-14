package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test //(enabled = false)
    public void testContactCreation() {

        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
        }
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Ed")
                .withLastName("JavaTest")
                .withAddress("Moscow City")
                .withHomePhone("+7(742)-1231231")
                .withMobilePhone("33-33-33")
                .withWorkPhone("22 22 22")
                .withEmail("adressbook@abmail.com")
                .withEmailTwo("adressbook2@abmail.com")
                .withEmailThree("adressbook3@abmail.com")
                .withGroup("Test1");
        app.contact().createContact(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

}
