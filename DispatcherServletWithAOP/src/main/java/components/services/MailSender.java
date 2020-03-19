package components.services;

import components.models.EmailMessage;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Setter
@PropertySource(value = "/")
public class MailSender {

    private String username;
    private String password;
    private Properties properties;

    public MailSender() {

    }

    public void send(EmailMessage emailMessage) {
        new Thread(() -> {
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessage.getEmailTo()));
                message.setSubject(emailMessage.getSubject());
                message.setContent(emailMessage.getText(), "text/html");
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}
