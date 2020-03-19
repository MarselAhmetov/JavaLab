package components.repositories;

import components.models.FileInfo;

public interface FileRepository {
    void save(FileInfo fileInfo);
    FileInfo getFile(Integer id);
    FileInfo getByOriginalName(String name);
}
