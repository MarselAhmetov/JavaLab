package team404.hateoas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Project extends RepresentationModel<Project> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date beginDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Command command;
    private String status;
    private String description;
    private String taskCode;
}
