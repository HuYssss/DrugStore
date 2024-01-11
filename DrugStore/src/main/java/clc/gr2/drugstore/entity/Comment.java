package clc.gr2.drugstore.entity;

import clc.gr2.drugstore.DTO.RequestDTO.CommentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Comment")
public class Comment {

    private ObjectId id;

    private String content;

    private ObjectId productId;

    private ObjectId userId;

    public Comment(CommentRequest commentRequest) {
        this.content = commentRequest.getContent();
        this.productId = commentRequest.getProductId();
    }
}
