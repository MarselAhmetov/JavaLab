package components.services;

import components.models.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFile(MultipartFile file, String email, String storageName);
    FileInfo getByOriginalName(String name);
}
