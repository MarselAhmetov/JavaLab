package team404.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.hateoas.domain.model.Command;
import team404.hateoas.domain.model.Project;
import team404.hateoas.repository.CommandRepository;
import team404.hateoas.repository.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Command closeCommand(Long commandId) {
        Optional<Command> commandOptional = commandRepository.findById(commandId);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            command.setStatus("closed");
            commandRepository.save(command);
            return command;
        }
        throw new IllegalArgumentException("Failure");
    }

    @Override
    public Project switchCode(Long projectId, String code) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setTaskCode(code);
            projectRepository.save(project);
            return project;
        }
        throw new IllegalArgumentException("Failure");
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.getOne(id);
    }
}
