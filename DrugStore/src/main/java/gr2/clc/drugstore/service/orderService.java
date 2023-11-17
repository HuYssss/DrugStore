package gr2.clc.drugstore.service;

import gr2.clc.drugstore.entity.orderDetail;
import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.order;

import java.util.Optional;


public interface orderService {
    public Iterable<order> getAll();
    public String saveOrUpdate(order order);
    public String delete(String id);
    public Optional<order> findById(String id);
    public Iterable<orderDetail> findAllOrderDetail(String id);
}
