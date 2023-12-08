package clc.gr2.drugstore.DTO.ResponseDTO;

import clc.gr2.drugstore.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private List<ProductItemResponse> productItems;

}
