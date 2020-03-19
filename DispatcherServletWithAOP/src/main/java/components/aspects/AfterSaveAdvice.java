package components.aspects;

import components.models.EmailMessage;

public interface AfterSaveAdvice {
    void after(EmailMessage message);
}
