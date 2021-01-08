package team404.dsl.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team404.dsl.model.Company;
import team404.dsl.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company/search")
    public ResponseEntity<List<Company>> searchByPredicate(@QuerydslPredicate(root = Company.class, bindings = CompanyRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(companyRepository.findAll(predicate).spliterator(), true)
                        .collect(Collectors.toList()));
    }
}
