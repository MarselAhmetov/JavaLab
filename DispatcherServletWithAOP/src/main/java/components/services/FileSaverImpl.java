package components.services;

import components.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileSaverImpl implements FileSaver {

    public void saveFile(MultipartFile file, String storageName) {
        String fileOriginalName = file.getOriginalFilename();
        String extension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        try {
            file.transferTo(new File("C://Users//Alfia//Desktop//JavaLab Repository//DispatcherServletWithAOP//src//main//resources//storage", storageName + extension));
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }
}
