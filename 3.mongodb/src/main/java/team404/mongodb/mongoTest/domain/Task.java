package team404.mongodb.mongoTest.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
@ToString
public class Task {
    @Id
    private String id;
    private String taskId;
    private Long hours;
    private String description;
    private Date creationDate;
    private String status;
}
