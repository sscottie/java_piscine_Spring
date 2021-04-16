package school21.spring.service.models;

import school21.spring.service.repositories.CrudRepository;

public class User {
    private Long     identifier;
    private String   email;

    public Long getId() {
        return identifier;
    }

    public String getEmail() {
        return email;
    }

    public User(Long identifier, String email) {
        this.identifier = identifier;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", email='" + email + '\'' +
                '}';
    }
}
