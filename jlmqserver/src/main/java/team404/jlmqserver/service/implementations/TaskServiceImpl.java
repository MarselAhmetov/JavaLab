package team404.jlmqserver.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.jlmqserver.model.Task;
import team404.jlmqserver.repository.TaskRepository;
import team404.jlmqserver.service.interfaces.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getByTaskId(String taskId) {
        return taskRepository.getByTaskId(taskId);
    }
}
