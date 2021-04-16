package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersServiceImpl usersService = context.getBean(UsersServiceImpl.class);
        HikariDataSource dataSource = context.getBean(HikariDataSource.class);
        createTable(dataSource);
        usersService.signUp("email");
        UsersRepositoryJdbcImpl usersRepositoryJdbc = context.getBean(UsersRepositoryJdbcImpl.class);
        System.out.println(usersRepositoryJdbc.findAll());
    }

    private static void example2(UsersRepositoryJdbcImpl usersRepositoryJdbc) throws SQLException {
        User userId = usersRepositoryJdbc.findById(3L);
        System.out.println(userId);
        User user = new User(13121993L, "sscottie@student.21school.ru");
        usersRepositoryJdbc.save(user);
        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbc.findAll());
        userId.setEmail("updateUser");
        usersRepositoryJdbc.update(userId);
        System.out.println(usersRepositoryJdbc.findById(3L));
        System.out.println(usersRepositoryJdbc.findByEmail("updateUser"));
        System.out.println(usersRepositoryJdbc.findAll());
    }

    private static void example1(UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        User userId = usersRepositoryJdbcTemplate.findById(3L);
        System.out.println(userId);
        User user = new User(15111995L, "suchmuchlunch@gmail.com");
        usersRepositoryJdbcTemplate.save(user);
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        usersRepositoryJdbcTemplate.delete(6L);
        userId.setEmail("updateUser");
        usersRepositoryJdbcTemplate.update(userId);
        System.out.println(usersRepositoryJdbcTemplate.findById(3L));
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("updateUser"));
        System.out.println(usersRepositoryJdbcTemplate.findAll());
    }

    private static void createTable(DataSource dataSource) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DROP TABLE IF EXISTS users;\n" +
                "CREATE TABLE users (id INT AUTO_INCREMENT  PRIMARY KEY, email VARCHAR(250) NOT NULL);\n" +
                "INSERT INTO users (email) VALUES\n" +
                "  ('suchmuchlunch@gmail.com'),\n" +
                "  ('suchmuchlunch1@gmail.com'),\n" +
                "  ('suchmuchlunch2@gmail.com'),\n" +
                "  ('suchmuchlunch3@gmail.com'),\n" +
                "  ('suchmuchlunch4@gmail.com');");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
