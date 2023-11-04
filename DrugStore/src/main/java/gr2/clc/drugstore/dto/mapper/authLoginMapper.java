package gr2.clc.drugstore.dto.mapper;

import gr2.clc.drugstore.dto.authLoginDTO;
import gr2.clc.drugstore.entity.user;

public class authLoginMapper {
    public static authLoginDTO getUserPassword(user user) {
        authLoginDTO authUser = null;


        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());

        return authUser;
    }
}
