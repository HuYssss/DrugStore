package clc.gr2.drugstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
public class Product {

	private ObjectId id;

	private String name;

	private double price;

	private String img;

	private String description;

	private String manufacturer;

	private ObjectId categoryId;
}
