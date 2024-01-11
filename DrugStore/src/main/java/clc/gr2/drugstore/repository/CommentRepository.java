package clc.gr2.drugstore.repository;

import clc.gr2.drugstore.entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
}
