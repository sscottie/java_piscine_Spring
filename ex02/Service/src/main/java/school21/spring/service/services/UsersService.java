package school21.spring.service.services;

import java.sql.SQLException;

public interface UsersService {

  String signUp(String email) throws SQLException;

}
