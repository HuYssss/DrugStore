package gr2.clc.drugstore.repository;

import gr2.clc.drugstore.entity.categoryEle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gr2.clc.drugstore.entity.category;

import java.util.Optional;

@Repository
public interface categoryRepository extends MongoRepository<category, String> {
    Optional<category> getByName(String name);
}
