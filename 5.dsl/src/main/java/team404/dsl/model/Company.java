package team404.dsl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "company")
public class Company implements Serializable {
    @Id
    private String id;
    private List<Country> countries;
    private String name;
    private String description;
    private String sphere;
    private String keyword;
    private Date creationDate;
}
