package clc.gr2.drugstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ProductItem")
public class ProductItem {

    private ObjectId id;

    private ObjectId productId;

    private int quantity;
}
