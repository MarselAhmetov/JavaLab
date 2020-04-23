package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.User;
import team404.project.model.dto.SignUpDto;
import team404.project.repository.interfaces.UserRepository;
import team404.project.service.interfaces.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    UserRepository userRepository;

    public boolean signUp(SignUpDto form) {
        userRepository.save(User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build());
        return true;
    }
}
