package components.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileSaver {
    void saveFile(MultipartFile file, String storageName);
}
