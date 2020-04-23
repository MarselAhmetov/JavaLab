package team404.project.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;
import team404.project.repository.interfaces.RoomRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class ChatController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/index")
    public String getChatPage(Model model, HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "username");
        if (cookie != null) {
            model.addAttribute("username", cookie.getValue());
        } else {
            model.addAttribute("username", "Null");
        }
        model.addAttribute("rooms", roomRepository.findAll());
        return "index";
    }
}
