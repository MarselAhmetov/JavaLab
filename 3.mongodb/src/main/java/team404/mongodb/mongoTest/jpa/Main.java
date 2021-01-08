package team404.mongodb.mongoTest.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team404.mongodb.mongoTest.domain.Task;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        RepositoryJpa repositoryJpa = context.getBean(RepositoryJpa.class);

      /*  Task task1 = Task.builder()
                .taskId("Task-1")
                .hours(100L)
                .status("active")
                .build();

        Task task2 = Task.builder()
                .taskId("Task-2")
                .hours(500L)
                .status("active")
                .build();

        Task task3 = Task.builder()
                .taskId("Task-3")
                .hours(1000L)
                .status("active")
                .build();

        repositoryJpa.save(task1);
        repositoryJpa.save(task2);
        repositoryJpa.save(task3);

        task1.setHours(200L);
        repositoryJpa.save(task1);*/

        List<Task> tasks = repositoryJpa.find("active", 700L);

        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
