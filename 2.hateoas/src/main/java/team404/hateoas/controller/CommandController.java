package team404.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team404.hateoas.service.ProjectService;

@RestController
public class CommandController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/command/{command-id}/closeCommand", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<?> closeCommand(@PathVariable("command-id") Long commandId) {
        return ResponseEntity.ok(
                EntityModel.of(projectService.closeCommand(commandId)));
    }

    /*@RequestMapping(value = "/command/{project-id}/switch", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> switchCode(@PathVariable("project-id") Long projectId, @RequestParam("newCode") String newCode) {
        Project project = projectService.getProjectById(projectId);
        project.add(linkTo(methodOn(CommandController.class).switchCode(projectId, newCode)).withSelfRel());

        return ResponseEntity.ok(EntityModel.of(projectService.switchCode(projectId, newCode)));
    }*/
}
