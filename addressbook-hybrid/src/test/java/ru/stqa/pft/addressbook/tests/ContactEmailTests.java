package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Edward on 13.09.2016.
 */
public class ContactEmailTests extends TestBase {
    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed")
                    .withLastName("JavaTest")
                    .withAddress("Moscow City")
                    .withHomePhone("8-495-1231231")
                    .withEmail("adressbook@abmail.com")
                    .withEmailTwo("adressbook2@abmail.com")
                    .withEmailThree("adressbook3@abmail.com"));
                    //.withGroup("Test1"));
        }
    }

    @Test//(enabled = false)

    public void emailTest() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));

    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmailTwo(),contact.getEmailThree())
                .stream()
                .filter((s) -> !s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

//    public static String cleaned(String email){
//        return email.replaceAll("\\s","").replaceAll("[-()]","");
//    }
    private static String cleaned(String email) {
        return email.trim();
    }
}
