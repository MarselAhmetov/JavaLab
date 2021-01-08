package team404.mongodb.mongoTest.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "employees")
@ToString
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfHiring;
    private String status;
    private Task activeTask;
    private Company company;

}
