package gr2.clc.drugstore.service;

import gr2.clc.drugstore.dto.authLoginDTO;
import gr2.clc.drugstore.entity.user;

import java.util.Optional;

public interface userService {
    public Iterable<user> getAll();
    public String saveOrUpdate(user user);
    public String delete(String id);
    public Optional<user> findById(String id);
    public Optional<user> findByAccount(authLoginDTO userLogin);
}
