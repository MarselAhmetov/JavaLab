package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.Authentication;
import team404.project.model.User;
import team404.project.model.dto.SignInDto;
import team404.project.repository.interfaces.UserRepository;
import team404.project.service.interfaces.SignInService;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Authentication authentication;

    public boolean signIn(SignInDto form) {
        User user = userRepository.getByUsername(form.getUsername());
        if (user.getPassword().equals(form.getPassword())) {
            authentication.setUsername(form.getUsername());
            authentication.setAuthenticated(true);
            return true;
        } else {
            return false;
        }
    }
}
