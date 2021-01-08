package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.hateoas.domain.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
