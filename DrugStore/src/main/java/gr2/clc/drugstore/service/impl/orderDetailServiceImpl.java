package gr2.clc.drugstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.orderDetail;
import gr2.clc.drugstore.repository.orderDetailRepository;
import gr2.clc.drugstore.service.orderDetailService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class orderDetailServiceImpl implements orderDetailService {
    @Autowired
    private orderDetailRepository repo;
    @Override
    public Iterable<orderDetail> getAll() {
        return this.repo.findAll();
    }

    @Override
    public void saveOrUpdate(orderDetail orderDetail) {
        repo.save(orderDetail);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<orderDetail> findById(String id) {
        return this.repo.findById(id);
    }
}
