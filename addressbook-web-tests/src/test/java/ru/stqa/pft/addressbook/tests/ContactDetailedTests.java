package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Edward on 14.09.2016.
 */
public class ContactDetailedTests extends TestBase {
    @BeforeMethod

    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact()
                    .createContact(new ContactData().withFirstName("Ed").withLastName("JavaTest").withAddress("Moscow City")
                            .withHomePhone("+7(999)999").withWorkPhone("33 33 33").withMobilePhone("22-22-22")
                            .withEmailOne("ab1@ab1.com").withEmailTwo("ab12@ab12.com").withEmailThree("ab123@ab123.com"));
        }
    }

    @Test
    public void testContactDetails(){

        app.contact().pressDetailButton();



    }
}
