package gr2.clc.drugstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gr2.clc.drugstore.entity.category;

@Repository
public interface categoryRepository extends MongoRepository<category, String> {
}
