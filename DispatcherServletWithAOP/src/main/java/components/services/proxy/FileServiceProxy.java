package components.services.proxy;

import components.aspects.AfterSaveAdvice;
import components.models.EmailMessage;
import components.models.FileInfo;
import components.services.FileService;
import components.services.MailSender;
import components.services.TemplateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service
public class FileServiceProxy implements FileService {

    @Autowired
    TemplateProcessor templateProcessor;
    @Qualifier("fileServiceImpl")
    @Autowired
    FileService target;
    @Autowired
    AfterSaveAdvice afterSaveAdvice;
    

    public void setAfterSaveAdvice(AfterSaveAdvice afterSaveAdvice) {
        this.afterSaveAdvice = afterSaveAdvice;
    }

    @Override
    public void saveFile(MultipartFile file, String email, String storageName) {
        target.saveFile(file, email, storageName);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("photoUrl", storageName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        afterSaveAdvice.after(EmailMessage.builder()
                .emailTo(email)
                .subject("File subject")
                .text(templateProcessor.processTemplate("ftl/email.ftl", parameters))
                .build());
    }

    @Override
    public FileInfo getByOriginalName(String name) {
        return target.getByOriginalName(name);
    }
}