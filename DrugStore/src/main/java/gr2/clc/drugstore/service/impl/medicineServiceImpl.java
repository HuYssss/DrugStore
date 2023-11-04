package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.medicine;
import gr2.clc.drugstore.repository.medicineRepository;
import gr2.clc.drugstore.service.medicineService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class medicineServiceImpl implements medicineService {
    @Autowired
    private medicineRepository repo;


    @Override
    public Iterable<medicine> getAll() {
        return this.repo.findAll();
    }

    @Override
    public void saveOrUpdate(medicine medicine) {
        repo.save(medicine);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<medicine> findById(String id) {
        return this.repo.findById(id);
    }

    @Override
    public Iterable<medicine> getByCateID(String id) {
        return null;
    }
}
