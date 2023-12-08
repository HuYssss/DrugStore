package clc.gr2.drugstore.DTO.ResponseDTO;

import clc.gr2.drugstore.entity.Order;
import clc.gr2.drugstore.entity.ProductItem;
import clc.gr2.drugstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private ObjectId id;

    private Date orderDate;

    private Double totalPrice;

    private List<ProductItemResponse> productItems;

    private User user;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.totalPrice = 0.0;
        this.productItems = new ArrayList<>();
        this.user = new User();
    }
}
