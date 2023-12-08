package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.AuthenticationRequestDTO;
import clc.gr2.drugstore.DTO.RequestDTO.UserRequestDTO;
import clc.gr2.drugstore.entity.User;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> register(UserRequestDTO userRes);
    ResponseEntity<?> login(AuthenticationRequestDTO authenticationRequestDTO);
    ResponseEntity<?> deleteUser(ObjectId id);
    User findUserByUsername(String username);

    ResponseEntity<?> getAllUser();
}
