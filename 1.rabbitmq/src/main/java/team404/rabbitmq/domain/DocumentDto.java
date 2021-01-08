package team404.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private String firstName;
    private String lastName;
    private Long documentNumber;
    private Integer age;
    private String documentReceiveDate;
}
