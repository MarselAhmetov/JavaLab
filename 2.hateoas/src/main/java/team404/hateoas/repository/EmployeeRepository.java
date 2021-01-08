package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.hateoas.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
