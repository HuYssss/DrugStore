package clc.gr2.drugstore.entity;

import clc.gr2.drugstore.DTO.RequestDTO.UserRequestDTO;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
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
