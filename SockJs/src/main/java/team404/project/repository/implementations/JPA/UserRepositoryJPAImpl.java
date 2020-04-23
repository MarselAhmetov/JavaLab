package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.User;
import team404.project.repository.interfaces.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository("userRepositoryJPAImpl")
@Transactional
public class UserRepositoryJPAImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User getByUsername(String username) {
        Query query = entityManager.createQuery("from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void saveAll(List<User> var1) {

    }

    @Override
    public Optional<User> findById(Integer id) {
        Query query = entityManager.createQuery("from User u where u.id = :id");
        query.setParameter("id", id);
        return Optional.of((User) query.getSingleResult());
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> var1) {
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
    public void delete(User var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> var1) {

    }

    @Override
    public void deleteAll() {

    }
}
