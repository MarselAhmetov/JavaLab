package components.repositories;

import components.models.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_INSERT = "insert into file_info (original_name, owner_email, size, storage_name, type, url) values (?, ?, ?, ?, ?, ?)";
    private final String SQL_SELECT_BY_ID = "select * from file_info where id = ?";
    private final String SQL_SELECT_BY_ORIGINALNAME = "select * from file_info where storage_name = ?";

    public RowMapper<FileInfo> fileInfoRowMapper = (row, rowNum) ->
            FileInfo.builder()
                    .id(row.getInt("id"))
                    .originalFileName(row.getString("original_name"))
                    .ownerEmail(row.getString("owner_email"))
                    .size(row.getLong("size"))
                    .storageFileName(row.getString("storage_name"))
                    .type(row.getString("type"))
                    .url(row.getString("url"))
                    .build();


    @Override
    public void save(FileInfo fileInfo) {
        jdbcTemplate.update(SQL_INSERT, fileInfo.getOriginalFileName(), fileInfo.getOwnerEmail(), fileInfo.getSize(), fileInfo.getStorageFileName(), fileInfo.getType(), fileInfo.getUrl());
    }

    @Override
    public FileInfo getFile(Integer id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{}, fileInfoRowMapper);
    }

    @Override
    public FileInfo getByOriginalName(String name) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ORIGINALNAME, new Object[]{name}, fileInfoRowMapper);
    }


}
