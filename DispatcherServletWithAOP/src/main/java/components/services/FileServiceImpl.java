package components.services;

import components.models.FileInfo;
import components.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileSaver fileSaver;
    @Autowired
    FileRepository fileRepository;

    @Override
    public void saveFile(MultipartFile file, String email, String storageName) {
        fileSaver.saveFile(file, storageName);
        FileInfo fileInfo = FileInfo.builder()
                .url("C:\\Users\\Alfia\\Desktop\\JavaLab Repository\\DispatcherServletWithAOP\\src\\main\\resources\\storage\\" + storageName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))
                .type(file.getContentType())
                .storageFileName(storageName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))
                .size(file.getSize())
                .ownerEmail(email)
                .originalFileName(file.getOriginalFilename())
                .build();
        fileRepository.save(fileInfo);
    }

    @Override
    public FileInfo getByOriginalName(String name) {
        return fileRepository.getByOriginalName(name);
    }
}
