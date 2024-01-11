package clc.gr2.drugstore.DTO.ResponseDTO;

import clc.gr2.drugstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private ObjectId id;
	private String email;
	private String phone;
	private String username;
	
	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.username = user.getUsername();
	}
}
