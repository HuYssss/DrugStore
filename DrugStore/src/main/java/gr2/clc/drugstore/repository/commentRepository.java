package gr2.clc.drugstore.repository;

import gr2.clc.drugstore.entity.comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface commentRepository extends MongoRepository<comment, String> {
}
