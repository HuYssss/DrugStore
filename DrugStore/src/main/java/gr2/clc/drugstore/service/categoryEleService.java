package gr2.clc.drugstore.service;

import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.categoryEle;

import java.util.Optional;


public interface categoryEleService {
    public Iterable<categoryEle> getAll();
    public void saveOrUpdate(categoryEle categoryEle);
    public void delete(String id);
    public Optional<categoryEle> findById(String id);
}
