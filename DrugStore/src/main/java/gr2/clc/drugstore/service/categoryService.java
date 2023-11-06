package gr2.clc.drugstore.service;

import org.springframework.stereotype.Service;
import gr2.clc.drugstore.entity.category;

import java.util.Optional;

public interface categoryService {
    public Iterable<category> getAll();
    public void saveOrUpdate(category category);
    public void delete(String id);
    public Optional<category> findById(String id);
    public void deleteCategoryEle(String cateId, String cateEleId);
}
