package gr2.clc.drugstore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class authRequestDTO {
    private String username;
    private String password;
}
