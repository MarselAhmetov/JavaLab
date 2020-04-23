package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team404.project.model.User;
import team404.project.repository.interfaces.UserRepository;
import team404.project.service.interfaces.UserService;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepositoryJPAImpl")
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Integer id) {
        Optional<User> user;
        if ((user = userRepository.findById(id)).isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
