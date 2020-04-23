package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import team404.project.model.Room;
import team404.project.repository.interfaces.RoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryJPAImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Room var1) {
        entityManager.persist(var1);
    }

    @Override
    public void saveAll(List<Room> var1) {

    }

    @Override
    public Optional<Room> findById(Integer var1) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<Room> findAll() {
        Query query = entityManager.createQuery("from Room");
        return query.getResultList();
    }

    @Override
    public Iterable<Room> findAllById(Iterable<Integer> var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer var1) {

    }

    @Override
    public void delete(Room var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends Room> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
