package junk;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;

/**
 * Created by horobets on Jul 04, 2019
 */
public class TestSendEmail {

    @Test
    public void asdasd() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
    }
}
