package clc.gr2.drugstore.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

	private ObjectId id;

	private String email;

	private String phone;

	private String username;

	private String password;

	private Set<Role> roles;
}
