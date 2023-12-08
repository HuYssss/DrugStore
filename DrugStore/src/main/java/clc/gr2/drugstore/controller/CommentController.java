package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.CommentRequest;
import clc.gr2.drugstore.entity.Comment;
import clc.gr2.drugstore.repository.CommentRepository;
import clc.gr2.drugstore.service.CommentService;
import clc.gr2.drugstore.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getCommentByProduct(@PathVariable(name = "productId")ObjectId productId) {
        return this.commentService.getCommentByProduct(productId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(CommentRequest commentRequest, Principal principal) {
        return this.commentService.createComment(commentRequest,
                this.userService.findUserByUsername(principal.getName())
                        .getId());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentRequest commentRequest
            , Principal principal, @PathVariable(name = "id") ObjectId commentId) {
        Comment updateComment = new Comment(commentRequest);
        updateComment.setId(commentId);
        updateComment.setUserId(this.userService.findUserByUsername(principal.getName()).getId());
        return this.commentService.updateComment(updateComment);
    }
}
