package gr2.clc.drugstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.order;
import gr2.clc.drugstore.repository.orderRepository;
import gr2.clc.drugstore.service.orderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class orderServiceImpl implements orderService {
    @Autowired
    private orderRepository repo;
    @Override
    public Iterable<order> getAll() {
        return this.repo.findAll();
    }

    @Override
    public void saveOrUpdate(order order) {
        repo.save(order);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<order> findById(String id) {
        return this.repo.findById(id);
    }
}
