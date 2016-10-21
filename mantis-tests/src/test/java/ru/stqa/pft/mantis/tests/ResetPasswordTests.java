package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Edward on 21.10.2016.
 */
public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailserver()

    {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException, ServiceException {
        String user = "user1477057950975";
        String password = "65ba305586bca0c22a702cec152e3159";
        String email = "user1477057950975@localhost.localdomain";
        app.sessionHelper().login("administrator", "root");
        app.resetPassword().reset("user1477057950975");
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailserver() {
        app.mail().stop();
    }
}
