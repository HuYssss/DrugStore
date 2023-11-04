package gr2.clc.drugstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gr2.clc.drugstore.entity.categoryEle;

import java.util.Optional;

@Repository
public interface categoryEleRepository extends MongoRepository<categoryEle, String> {
    Optional<categoryEle> getByName(String name);
}
