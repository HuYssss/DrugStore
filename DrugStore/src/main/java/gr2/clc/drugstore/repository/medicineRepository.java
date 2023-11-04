package gr2.clc.drugstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gr2.clc.drugstore.entity.medicine;

import java.util.Optional;

@Repository
public interface medicineRepository extends MongoRepository<medicine, String> {
    Optional<medicine> getByName(String name);
}
