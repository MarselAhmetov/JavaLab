package team404.dsl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import team404.dsl.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

}
