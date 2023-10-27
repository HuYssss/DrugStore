package gr2.clc.drugstore.service;

import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.order;

import java.util.Optional;


public interface orderService {
    public Iterable<order> getAll();
    public void saveOrUpdate(order order);
    public void delete(String id);
    public Optional<order> findById(String id);
}
