package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Edward on 14.09.2016.
 */
public class ContactDetailedTests extends TestBase {
    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact()
                    .createContact(new ContactData()
                            .withFirstName("Ed")
                            .withLastName("JavaTest")
                            .withAddress("Moscow City")
                            .withHomePhone("+7(742)-1231231")
                            .withMobilePhone("33-33-33")
                            .withWorkPhone("22 22 22")
                            .withEmail("adressbook@abmail.com")
                            .withEmailTwo("adressbook2@abmail.com")
                            .withEmailThree("adressbook3@abmail.com"));
        }
    }

    @Test
    public void testContactDetails(){

        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData infoFromDetailPage = app.contact().infoFromDetailPage(contact);
        System.out.println(mergeDetail(infoFromDetailPage));
        System.out.println(infoFromEditForm.getAllData());

        assertThat(infoFromEditForm.getAllData(), equalTo(mergeDetail(infoFromDetailPage)));


    }

    private String mergeDetail(ContactData contact) {
        return Arrays.asList(contact.getFirstName(),
                contact.getLastName(),
                contact.getAddress(),
                contact.getHomePhone(),
                contact.getMobilePhone(),
                contact.getWorkPhone(),
                contact.getEmail(),
                contact.getEmailTwo(),
                contact.getEmailThree())
                .stream()
                .filter((s) -> !s.equals(""))
                .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining(""));
    }

    public static String cleanedDetails (String details){
        return details.replaceAll("H:", "")
                .replaceAll("M:", "")
                .replaceAll("W:", "")
                .replaceAll("\\s", "")
                .replaceAll("\n", "")
                .replaceAll("\\(w.*\\)", "");
               // .replaceAll("[w_(?=()]", "")
                //.replaceAll("^w(.*+^m)","");
                //.replaceAll("\\([a-z0-9_.-]*\\)", "");
                //.replaceAll("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z])","");

    }
}
