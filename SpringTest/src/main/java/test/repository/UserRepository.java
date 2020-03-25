package test.repository;

import test.model.User;

public interface UserRepository  {
    User findUserByEmail(String email);
    void save(User user);
}
