package clc.gr2.drugstore.entity;

import clc.gr2.drugstore.DTO.RequestDTO.AddressRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Address")
public class Address {

	private ObjectId id;

	private String address;

	private String city;

	private String country;

	private ObjectId user;

	public Address(AddressRequestDTO addressRequestDTO) {
		this.id = new ObjectId();
		this.address = addressRequestDTO.getAddress();
		this.city = addressRequestDTO.getCity();
		this.country = addressRequestDTO.getCountry();
	}
}
