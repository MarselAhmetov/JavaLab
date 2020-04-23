package team404.project.model;

import lombok.Data;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Data
public class Authentication {
    String username;
    boolean isAuthenticated;
}
