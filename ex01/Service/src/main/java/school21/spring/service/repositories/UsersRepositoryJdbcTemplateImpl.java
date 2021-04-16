package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {
    private final String tableName;
    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("email"));
    };

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource,  String tableName) {
        super(dataSource);
        this.tableName = tableName;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
