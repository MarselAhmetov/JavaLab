package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.hateoas.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
