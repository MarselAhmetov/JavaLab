package team404.mongodb.mongoTest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "companies")
public class Company {
    @Id
    private String id;
    private String name;
    private String description;
    private String sphere;
    private String keyword;
    private Date creationDate;
}
