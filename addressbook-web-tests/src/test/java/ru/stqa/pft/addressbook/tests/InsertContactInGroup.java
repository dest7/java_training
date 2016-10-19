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

public class InsertContactInGroup extends TestBase{
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

//    @Test
//    public void testContactInGroup() {
//        Groups groupsBefore = app.db().groups();
//        Contacts contactsBefore = app.db().contacts();
//        ContactData selectedContact = contactsBefore.iterator().next();
//
//        Groups groupsOfSelectedContact = selectedContact.getGroups();
//        GroupData selectedGroup = groupsBefore.iterator().next();
//        Iterator<ContactData> iteratorContacts = contactsBefore.iterator();
//
//        while (iteratorContacts.hasNext()) {
//            if (groupsOfSelectedContact.equals(groupsBefore)) {
//                selectedContact = iteratorContacts.next();
//                groupsOfSelectedContact = selectedContact.getGroups();
//            } else break;
//        }
//        if (groupsOfSelectedContact.equals(groupsBefore)) {
//            app.goTo().groupPage();
//            app.group().create(new GroupData().withName("Test1"));
//        }
//
//        app.goTo().homePage();
//        app.contact().selectContactById(selectedContact.getId());
//        app.contact().addToGroupById(selectedGroup.getId());
//        app.goTo().selectGroupOnHomePage();
//
//        assertThat(selectedContact.getGroups(), equalTo(groupsOfSelectedContact));
//
//    }

    @Test
    public void testAddContactInGroup() {

         Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

//        Contacts contacts = app.contact().all();
//        Groups groups = app.group().all();

        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().ContactAddToGroup(contact, group);
        group.withId(app.db().groups().stream().mapToInt(g -> g.getId()).max().getAsInt());
        List<ContactData> after = new ArrayList<>();
        after.addAll(app.db().contacts());
        ContactData editedContact = after.get(after.indexOf(contact));
        assertThat(contact.getGroups().withAdded(group), equalTo(editedContact.getGroups()));
    }


    // Этот тест для удалиния контакта из группы помещен тут, чтобы можно было подряд запускать, и один и тот же
    // контакт, будет перемещен в группу, а потом из группы.
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
