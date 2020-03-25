package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import test.model.SignInDto;
import test.model.User;
import test.repository.UserRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void signIn(SignInDto form) {
        User userOptional = userRepository.findUserByEmail(form.getEmail());
    }
}
