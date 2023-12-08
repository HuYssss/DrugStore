package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.CommentRequest;
import clc.gr2.drugstore.entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<?> getCommentByProduct(ObjectId productId);
    ResponseEntity<?> createComment(CommentRequest comment, ObjectId userId);
    ResponseEntity<?> updateComment(Comment comment);
    ResponseEntity<?> deleteComment(ObjectId commentId);
}
