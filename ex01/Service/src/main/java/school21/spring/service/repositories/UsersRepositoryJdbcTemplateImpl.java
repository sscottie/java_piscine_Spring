package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;

    public UsersRepositoryJdbcTemplateImpl(HikariDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("users");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.email = '" + email + "'", new UsersRowMapper()));
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = " + id, new UsersRowMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper(User.class));
    }

    @Override
    public void save(User entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", entity.getEmail());
        jdbcInsert.execute(parameters);
    }

    @Override
    public void update(User entity) {
        String sql = "update users set email=? where id=?";
        jdbcTemplate.update(sql, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "delete from users where id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }
}

class UsersRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        return new User(id, email);
    }
}


