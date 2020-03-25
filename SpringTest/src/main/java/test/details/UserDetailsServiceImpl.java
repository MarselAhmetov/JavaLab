package test.details;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.model.User;
import test.repository.UserRepository;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /*User user = User.builder()
                .id(1L)
                .email("marsel5027@gmail.com")
                .createdAt(LocalDateTime.parse("2020-03-25T20:56:17.398"))
                .hashPassword("$2a$10$X3zH6ztX3Cub1/VrseVaPO0gmew2WH24BYzXvv3G12o2Fg1LrCC5u")
                .name("Marsel")
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .build();
        System.out.println(user);
        return new UserDetailsImpl(user);*/
        User user = userRepository.findUserByEmail(email);
        System.out.println(user);
            return new UserDetailsImpl(user);
    }
}
