package clc.gr2.drugstore.DTO.RequestDTO;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
