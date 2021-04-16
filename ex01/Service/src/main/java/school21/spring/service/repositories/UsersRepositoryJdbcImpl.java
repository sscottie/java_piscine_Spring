package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    public DataSource dataSource;
    private final String tableName;

    public UsersRepositoryJdbcImpl(DataSource dataSource, String tableName) {
        this.tableName = tableName;
        this.dataSource = dataSource;
    }

    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email){
        return Optional.empty();
    }

    public List<User> findAll() {

        return null;
    }

    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
