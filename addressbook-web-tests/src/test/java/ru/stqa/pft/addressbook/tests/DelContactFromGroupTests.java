package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Edward on 19.10.2016.
 */
public class DelContactFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {

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

    @Test
    public void testDeleteContactFromGroup() {
        Groups groupsBefore = app.db().groups();
        Contacts contactsBefore = app.db().contacts();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData selectedGroup = groupsBefore.iterator().next();

        Groups groupsOfSelectedContact = selectedContact.getGroups();
        if (groupsOfSelectedContact.size() == 0) {
            app.contact().selectContactById(selectedContact.getId());
            app.contact().addToGroupById(selectedGroup.getId());
        }

      //  Iterator<ContactData> iteratorContacts = contactsBefore.iterator();
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

//        Contacts contacts = app.contact().all();
//        Groups groups = app.group().all();

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contact, group);
        List<ContactData> after = new ArrayList<>();
        after.addAll(app.db().contacts());
        ContactData editedContact = after.get(after.indexOf(contact));
        assertThat(editedContact.getGroups(), equalTo(contact.getGroups().withOut(group)));
    }

}

