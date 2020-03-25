package test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import test.model.Role;
import test.model.State;
import test.model.User;

import java.time.LocalDateTime;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_USER = "insert into user (email, name, hashPassword, role) values (?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_EMAIL = "select * from user where email = ?";

    public RowMapper<User> userRowMapper = (row, rowNum) ->
            User.builder()
                    .id(row.getLong("id"))
                    .email(row.getString("email"))
                    .name(row.getString("name"))
                    .hashPassword(row.getString("hashPassword"))
                    .role(Role.valueOf(row.getString("role")))
                    .build();

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getEmail(), user.getName(), user.getHashPassword(), user.getRole().toString());
    }
}
