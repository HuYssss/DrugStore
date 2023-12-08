package clc.gr2.drugstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Cart")
public class Cart {

	private ObjectId id;

	private List<ObjectId> productItems;

	private ObjectId userId;
}
