package components.controllers;

import components.services.FileSaver;
import components.services.FileSaverImpl;
import components.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

@Controller
public class FilesController {

    @Qualifier("fileServiceProxy")
    @Autowired
    FileService fileService;

    @PostMapping("/files")
    public ModelAndView uploadFilePost(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) throws IOException {
        fileService.saveFile(file, email, UUID.randomUUID().toString());
        return null;
    }


    // localhost:8080/files/123809183093qsdas09df8af.jpeg

    @RequestMapping(value ="/files" , method = RequestMethod.GET)
    public ModelAndView getFile(@RequestParam("link") String link) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("image", fileService.getByOriginalName(link).getUrl());
        modelAndView.setViewName("file_view");
        // TODO: найти на диске
        // TODO: отдать пользователю
        return modelAndView;
    }
}
