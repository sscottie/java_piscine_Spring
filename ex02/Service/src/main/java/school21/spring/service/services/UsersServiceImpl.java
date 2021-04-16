package school21.spring.service.services;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

  @Autowired
  @Qualifier("usersRepositoryJdbc")
  UsersRepository usersRepository;

  @Override
  public String signUp(String email) throws SQLException {
    String password = UUID.randomUUID().toString();
    User newUser = new User(email, password);
    usersRepository.save(newUser);
    return null;
  }
}
