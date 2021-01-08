package team404.dsl.repository;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import team404.dsl.model.Company;
import team404.dsl.model.QCompany;

public interface CompanyRepository extends MongoRepository<Company, String>, QuerydslPredicateExecutor<Company>, QuerydslBinderCustomizer<QCompany> {
    @Override
    default void customize(QuerydslBindings bindings, QCompany qCompany) {
        bindings.bind(qCompany.countries.any().name).as("countries.name").first(
                StringExpression::containsIgnoreCase
        );
    }
}
