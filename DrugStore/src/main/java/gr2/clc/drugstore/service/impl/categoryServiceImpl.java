package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.category;
import gr2.clc.drugstore.repository.categoryRepository;
import gr2.clc.drugstore.service.categoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    private categoryRepository repo;

    @Autowired
    private medicineService repoMedicine;

    @Override
    public Iterable<category> getAll() {
        return repo.findAll();
    }

    @Override
    public void saveOrUpdate(category category) {
        repo.save(category);
    }

    @Override
    public void delete(String id) {

        repo.deleteById(id);
    }

    @Override
    public Optional<category> findById(String id) {
        return repo.findById(id);
    }
}
