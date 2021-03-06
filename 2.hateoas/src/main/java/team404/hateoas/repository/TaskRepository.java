package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.hateoas.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
