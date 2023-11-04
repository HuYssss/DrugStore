package gr2.clc.drugstore.service;

import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.medicine;

import java.util.Optional;


public interface medicineService {
    public Iterable<medicine> getAll();
    public void saveOrUpdate(medicine medicine);
    public void delete(String id);
    public Optional<medicine> findById(String id);

    public Iterable<medicine> getByCateID(String id);
}
