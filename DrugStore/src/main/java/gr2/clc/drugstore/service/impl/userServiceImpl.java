package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.user;
import gr2.clc.drugstore.repository.userRepository;
import gr2.clc.drugstore.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepository repo;

    @Override
    public Iterable<user> getAll() {
        return this.repo.findAll();
    }

    @Override
    public void saveOrUpdate(user user) {
        repo.save(user);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<user> findById(String id) {
        return this.repo.findById(id);
    }
}
