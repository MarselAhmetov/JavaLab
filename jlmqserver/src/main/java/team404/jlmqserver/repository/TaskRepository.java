package team404.jlmqserver.repository;

import org.springframework.data.repository.CrudRepository;
import team404.jlmqserver.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task getByTaskId(String taskId);
}
