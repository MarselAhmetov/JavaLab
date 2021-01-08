package team404.mongodb.mongoTest.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import team404.mongodb.mongoTest.domain.Task;

import java.util.List;

public interface RepositoryJpa extends MongoRepository<Task, String> {

    @Query(value = "{$and: [{status: ?0}, {hours: {$lt: ?1}}]}")
    List<Task> find(@Param("status") String status, @Param("hours") Long hours);
}
