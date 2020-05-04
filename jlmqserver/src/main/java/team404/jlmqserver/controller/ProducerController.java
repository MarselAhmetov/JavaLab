package team404.jlmqserver.controller;

import messages.TaskMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@Controller
public class ProducerController {

    @Autowired
    private Map<String, Queue<TaskMessage>> queues;

    @GetMapping("/produce")
    public ModelAndView getProducePage() {
        return new ModelAndView("produce");
    }
}
