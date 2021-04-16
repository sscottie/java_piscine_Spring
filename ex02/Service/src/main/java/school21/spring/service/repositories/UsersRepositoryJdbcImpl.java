package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String emaill = resultSet.getString("email");
            String password = resultSet.getString("password");
            preparedStatement.close();
            return Optional.of(new User(id, emaill, password));
        }
        preparedStatement.close();
        return Optional.empty();
    }

    @Override
    public User findById(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = " + id;
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Long idd = resultSet.getLong("id");
            String emaill = resultSet.getString("email");
            String password = resultSet.getString("password");
            preparedStatement.close();
            return new User(idd, emaill, password);
        }
        preparedStatement.close();
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {

            Long idd = resultSet.getLong("id");
            String emaill = resultSet.getString("email");
            String password = resultSet.getString("password");
            users.add(new User(idd, emaill, password));
        }
        preparedStatement.close();
        return users;
    }

    @Override
    public void save(User entity) throws SQLException {
        String sql = "INSERT INTO users (email, password) VALUES ('" + entity.getEmail() + "', '" + entity.getPassword() + "')";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void update(User entity) throws SQLException {
        String sql = "update users set email=?, password = ? where id=?";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setLong(3, entity.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete from users where id = " + id;
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
