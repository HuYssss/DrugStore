package gr2.clc.drugstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gr2.clc.drugstore.entity.categoryEle;

@Repository
public interface categoryEleRepository extends MongoRepository<categoryEle, String> {
}
