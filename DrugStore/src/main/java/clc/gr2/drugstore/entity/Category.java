package clc.gr2.drugstore.entity;

import clc.gr2.drugstore.DTO.RequestDTO.CategoryRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Category")
public class Category {

	private ObjectId id;

	private String categoryName;

	private List<ObjectId> products;

	public Category(CategoryRequestDTO categoryRequestDTO) {
		this.id = new ObjectId();
		this.categoryName = categoryRequestDTO.getCategoryName();
		this.products = categoryRequestDTO.getProducts();
	}

	public void setProduct(ObjectId product) {
		this.products.add(product);
	}
}
