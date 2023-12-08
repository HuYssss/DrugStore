package clc.gr2.drugstore.DTO.RequestDTO;

import clc.gr2.drugstore.entity.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Date orderDate;
    private double totalPrice;
    private List<ObjectId> productItem;
    private ObjectId user;
}
