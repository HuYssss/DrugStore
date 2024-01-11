package clc.gr2.drugstore.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemRequest {
    private ObjectId product;
    private int quantity;
}
