package components.aspects;

import components.models.EmailMessage;
import components.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AfterSaveAdviceImpl implements AfterSaveAdvice{

    @Autowired
    MailSender mailSender;

    @Override
    public void after(EmailMessage message) {
        mailSender.send(message);
    }
}
