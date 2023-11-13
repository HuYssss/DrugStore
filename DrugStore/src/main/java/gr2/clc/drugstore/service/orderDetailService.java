package gr2.clc.drugstore.service;

import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.orderDetail;

import java.util.Optional;


public interface orderDetailService {
    public Iterable<orderDetail> getAll();
    public String saveOrUpdate(orderDetail orderDetail);
    public String delete(String id);
    public Optional<orderDetail> findById(String id);
}
