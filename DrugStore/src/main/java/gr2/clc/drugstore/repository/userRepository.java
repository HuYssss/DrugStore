package gr2.clc.drugstore.repository;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import gr2.clc.drugstore.entity.user;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends MongoRepository<user, String> {
    Optional<user> findByUsernameAndPassword(String username, String password);
}