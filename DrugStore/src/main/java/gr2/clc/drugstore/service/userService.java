package gr2.clc.drugstore.service;

import gr2.clc.drugstore.entity.user;

import java.util.Optional;

public interface userService {
    public Iterable<user> getAll();
    public void saveOrUpdate(user user);
    public void delete(String id);
    public Optional<user> findById(String id);
}
