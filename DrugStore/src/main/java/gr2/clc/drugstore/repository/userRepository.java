package gr2.clc.drugstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import gr2.clc.drugstore.entity.user;


public interface userRepository extends MongoRepository<user, String> {
}