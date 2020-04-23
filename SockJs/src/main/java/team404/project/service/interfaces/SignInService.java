package team404.project.service.interfaces;

import team404.project.model.dto.SignInDto;

public interface SignInService {
    boolean signIn(SignInDto signInDto);
}
