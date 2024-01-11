package clc.gr2.drugstore.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    private String categoryName;

    private List<ObjectId> products;

    public CategoryRequestDTO(String categoryName) {
        this.categoryName = categoryName;
        this.products = null;
    }

}
