package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.hateoas.domain.model.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
