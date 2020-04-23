package team404.project.repository.interfaces;


import team404.project.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User getByUsername(String username);
}
