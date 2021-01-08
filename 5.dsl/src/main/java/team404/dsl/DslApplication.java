package team404.dsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import team404.dsl.model.Company;
import team404.dsl.model.Country;
import team404.dsl.repository.CompanyRepository;
import team404.dsl.repository.CountryRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class DslApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(DslApplication.class, args);
        CompanyRepository companyRepository = context.getBean(CompanyRepository.class);
        CountryRepository countryRepository = context.getBean(CountryRepository.class);

        /*Country russia = Country.builder()
                .name("Russia")
                .build();

        Country usa = Country.builder()
                .name("USA")
                .build();

        Country china = Country.builder()
                .name("China")
                .build();

        countryRepository.save(russia);
        countryRepository.save(usa);
        countryRepository.save(china);

        List<Country> list = new LinkedList();
        list.add(russia);
        list.add(usa);
        Company company1 = Company.builder()
                .name("Company1")
                .keyword("keyword1")
                .countries(list)
                .build();

        list = new LinkedList();
        list.add(china);
        list.add(usa);
        Company company2 = Company.builder()
                .name("Company2")
                .keyword("keyword2")
                .countries(list)
                .build();

        list = new LinkedList();
        list.add(russia);
        list.add(china);
        Company company3 = Company.builder()
                .name("Company3")
                .keyword("keyword3")
                .countries(list)
                .build();

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);*/
    }

}
