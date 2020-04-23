package team404.project.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.dto.SignInDto;
import team404.project.service.implementations.SignInServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Autowired
    SignInServiceImpl signInService;

    @GetMapping("/signIn")
    public ModelAndView getLoginPage() {
        return new ModelAndView("signin");
    }

    @PostMapping("/signIn")
    public String signIn(HttpServletResponse response, SignInDto form) {
        if (signInService.signIn(form)) {
            Cookie cookie = new Cookie("username", form.getUsername());
            response.addCookie(cookie);
        }
        return "redirect:/index";
    }
}
