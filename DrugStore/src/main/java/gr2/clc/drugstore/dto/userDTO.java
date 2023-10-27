package gr2.clc.drugstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int isAdmin;
    private int isUser;
}
