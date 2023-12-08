package clc.gr2.drugstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Order")
public class Order {

	private ObjectId id;
	
	private Date orderDate;

	private Double totalPrice;

	private List<ObjectId> productItems;

	private ObjectId user;

	private ObjectId addressId;
}
