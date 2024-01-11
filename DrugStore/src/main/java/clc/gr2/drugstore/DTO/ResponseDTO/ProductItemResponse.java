package clc.gr2.drugstore.DTO.ResponseDTO;

import clc.gr2.drugstore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponse {
    private Product product;
    private int quantity;
}
