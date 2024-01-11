package clc.gr2.drugstore.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;

    private double price;

    private String img;

    private String description;

    private String manufacturer;
}
