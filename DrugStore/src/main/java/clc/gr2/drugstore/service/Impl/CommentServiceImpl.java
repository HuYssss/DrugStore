package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.CommentRequest;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.entity.Comment;
import clc.gr2.drugstore.repository.CommentRepository;
import clc.gr2.drugstore.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceBase implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public ResponseEntity<?> getCommentByProduct(ObjectId productId) {
        List<Comment> comments = this.commentRepository.findAll();
        for (Comment c : comments) {
            if (!c.getProductId().equals(productId))
                comments.remove(c);
        }
        return success(comments);
    }

    @Override
    public ResponseEntity<?> createComment(CommentRequest comment, ObjectId userId) {
        Comment newComment = new Comment();
        newComment.setId(new ObjectId());
        newComment.setProductId(comment.getProductId());
        newComment.setContent(comment.getContent());
        newComment.setUserId(userId);

        this.commentRepository.save(newComment);

        return success(newComment);
    }

    @Override
    public ResponseEntity<?> updateComment(Comment comment) {
        this.commentRepository.save(comment);
        return success(comment);
    }

    @Override
    public ResponseEntity<?> deleteComment(ObjectId commentId) {
        this.commentRepository.deleteById(commentId);
        return success(commentId);
    }
}
