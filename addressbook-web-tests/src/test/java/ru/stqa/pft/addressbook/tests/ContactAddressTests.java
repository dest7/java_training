package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Edward on 12.09.2016.
 */
public class ContactAddressTests extends TestBase {
    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData()
                    .withFirstName("Ed").withLastName("JavaTest").withAddress("Moscow City")
                    .withHomePhone("8-495-1231231").withEmail("adressbook@abmail.com")/*.withGroup("Test1")*/);
        }
    }

    @Test//(enabled = false)

    public void addressTest() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream()
                .filter((s) -> !s.isEmpty())
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining(" "));
    }


//    public static String cleaned(String address){
//        return address.replaceAll("\\s","").replaceAll("[-()]","");
//    }
    private static String cleaned(String address) {
        return address.replaceAll("(?m)^[ ]+", "");

    }
}

