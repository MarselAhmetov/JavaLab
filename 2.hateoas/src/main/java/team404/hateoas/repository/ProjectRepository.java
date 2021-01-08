package team404.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import team404.hateoas.domain.model.Command;
import team404.hateoas.domain.model.Project;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @RestResource(path = "switchCode", rel = "switchCode")
    @Modifying
    @Transactional
    @Query("update Project set taskCode = :code where id = :id")
    void switchCode(@Param("id") Long id, @Param("code") String code);

}
