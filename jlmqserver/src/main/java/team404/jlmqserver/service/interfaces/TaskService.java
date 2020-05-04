package team404.jlmqserver.service.interfaces;

import team404.jlmqserver.model.Task;

public interface TaskService {
    void save(Task task);
    Task getByTaskId(String taskId);
}
