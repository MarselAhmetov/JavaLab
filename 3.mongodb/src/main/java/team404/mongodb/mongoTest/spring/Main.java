package team404.mongodb.mongoTest.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import team404.mongodb.mongoTest.domain.Employee;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

        Employee employee = Employee.builder()
                .firstName("Marsel")
                .lastName("Marselev")
                .status("active")
                .build();
        template.save(employee);

        template.remove(new Query(where("firstName").is("Marsel")), Employee.class);

        Employee employee1 = Employee.builder()
                .firstName("Marsel")
                .lastName("Marselev")
                .status("active")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("NeMarsel")
                .lastName("Marselev")
                .status("notActive")
                .build();

        template.save(employee1);
        template.save(employee2);

        List<Employee> employees = template.find(new Query(
                where("lastName").is("Marselev")
                        .orOperator(
                                where("firstName").is("Marsel"),
                                where("status").is("notActive"))), Employee.class, "employees");

        for (Employee employee3 : employees) {
            System.out.println(employee3);
        }

    }
}
