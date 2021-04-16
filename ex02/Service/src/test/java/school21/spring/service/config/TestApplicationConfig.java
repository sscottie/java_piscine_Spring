package school21.spring.service.config;

import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
public class TestApplicationConfig {
    @Bean
    public DataSource embeddedDatabase() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("shema.sql")
                .build();
    }

    @Bean
    public UsersRepository usersRepositoryJdbc(DataSource dataSource) {
        return new UsersRepositoryJdbcImpl(dataSource);
    }

    @Bean
    public UsersRepository usersRepositoryJdbcTemplate(DataSource dataSource) {
        return new UsersRepositoryJdbcTemplateImpl(dataSource);
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl();
    }
}
