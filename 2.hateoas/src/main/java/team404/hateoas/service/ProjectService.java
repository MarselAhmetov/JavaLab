package team404.hateoas.service;

import team404.hateoas.domain.model.Command;
import team404.hateoas.domain.model.Project;

public interface ProjectService {
    Command closeCommand(Long commandId);
    Project switchCode(Long projectId, String code);
    Project getProjectById(Long id);
}
