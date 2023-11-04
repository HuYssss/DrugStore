package gr2.clc.drugstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.categoryEle;
import gr2.clc.drugstore.repository.categoryEleRepository;
import gr2.clc.drugstore.service.categoryEleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class categoryEleServiceImpl implements categoryEleService {
    @Autowired
    private categoryEleRepository repo;

    @Override
    public Iterable<categoryEle> getAll() {
        return this.repo.findAll();
    }

    @Override
    public void saveOrUpdate(categoryEle categoryEle) {
        repo.save(categoryEle);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<categoryEle> findById(String id) {
        return this.repo.findById(id);
    }
}
