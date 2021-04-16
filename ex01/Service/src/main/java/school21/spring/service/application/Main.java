package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        User test1 = new User(123456L, "trepadeira0ru@gmail.com");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        User test2 = new User(123454L, "suchmuchlunch@gmail.com");
        System.out.println(usersRepository.findAll());
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
    }
}
