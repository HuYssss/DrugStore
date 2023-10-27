package gr2.clc.drugstore.dto.mapper;

import gr2.clc.drugstore.dto.userDTO;
import gr2.clc.drugstore.entity.user;

public class userMapper {
    public static userDTO convertDTO(user user) {
        userDTO tmp = new userDTO();

        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setAddress(user.getAddress());
        tmp.setPhone(user.getPhone());
        tmp.setEmail(user.getEmail());
        tmp.setIsAdmin(user.getIsAdmin());
        tmp.setIsUser(user.getIsUser());

        return tmp;
    }
}
