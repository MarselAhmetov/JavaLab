package team404.hateoas.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import team404.hateoas.controller.CommandController;
import team404.hateoas.domain.model.Command;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommandRepresentationModelProcessor implements RepresentationModelProcessor<EntityModel<Command>> {
    
    @Override
    public EntityModel<Command> process(EntityModel<Command> model) {
        Command command = model.getContent();
        if (command != null) {
            model.add(linkTo(methodOn(CommandController.class).closeCommand(command.getId())).withRel("closeCommand"));
        }

        return model;
    }



}
