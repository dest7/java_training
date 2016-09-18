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
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailPage = app.contact().infoFromDetailPage(contact);

        System.out.println(mergeAllInfo(contactInfoFromEditForm));
        System.out.println(contactInfoFromDetailPage.getDataFromDetailPage());

        //assertThat(mergeDetail(contactInfoFromEditForm), equalTo(contactInfoFromDetailPage.getDataFromDetailPage()));
        assertThat(contactInfoFromDetailPage.getDataFromDetailPage(), equalTo(mergeAllInfo(contactInfoFromEditForm)));

    }

    private String mergeAllInfo(ContactData contact) {

        String firstAndLastName = Arrays.asList(contact.getFirstName(), contact.getLastName())
                .stream().filter(s -> !(s == null) && !s.equals(""))
              //  .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining(" "));

        String address = Arrays.asList(contact.getAddress())
                .stream().filter(s -> !(s == null) && !s.equals(""))
            //    .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n"));

        String homePhone = "H: " + Arrays.asList(contact.getHomePhone())
                .stream().filter(s -> !(s == null) && !s.equals(""))
              //  .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n")) + "\n";

        String mobilePhone = "M: " + Arrays.asList(contact.getMobilePhone())
                .stream().filter(s -> !(s == null) && !s.equals(""))
              //  .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n")) + "\n";

        String workPhone = "W: " + Arrays.asList(contact.getWorkPhone())
                .stream().filter(s -> !(s == null) && !s.equals(""))
               // .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n")) + "\n";

        String email = Arrays.asList(contact.getEmail())
                .stream().filter(s -> !(s == null) && !s.equals(""))
                .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n"));

        String emailTwo = Arrays.asList(contact.getEmailTwo())
                .stream().filter(s -> !(s == null) && !s.equals(""))
              //  .map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n"));

        String emailThree = Arrays.asList(contact.getEmailThree())
                .stream().filter(s -> !(s == null) && !s.equals(""))
                //.map(ContactDetailedTests::cleanedDetails)
                .collect(Collectors.joining("\n"));

        return firstAndLastName + "\n" + address + "\n\n" + homePhone + mobilePhone + workPhone + "\n" + email +
                " (www." + email.substring(email.indexOf("@") + 1) + ")\n" +
                emailTwo + " (www." + emailTwo.substring(emailTwo.indexOf("@") + 1) + ")\n" +
                emailThree + " (www." + emailThree.substring(emailThree.indexOf("@") + 1) + ")";

//        return Arrays.asList(contact.getFirstName(),
//                contact.getLastName(),
//                contact.getAddress(),
//                contact.getHomePhone(),
//                contact.getMobilePhone(),
//                contact.getWorkPhone(),
//                contact.getEmail(),
//                contact.getEmailTwo(),
//                contact.getEmailThree())
//                .stream()
//                .filter((s) -> !s.equals(""))
//                .map(ContactDetailedTests::cleanedDetails)
//                .collect(Collectors.joining("\n"));
    }

    public static String cleanedDetails (String details){
        return details
              //  .replaceAll("M:", "")
               // .replaceAll("W:", "")
                .replaceAll("\\s", "")
                .replaceAll("\n", "");
                //.replaceAll("\\(w.*\\)", "");
               // .replaceAll("[w_(?=()]", "")
                //.replaceAll("^w(.*+^m)","");
                //.replaceAll("\\([a-z0-9_.-]*\\)", "");
                //.replaceAll("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z])","");

    }
}
